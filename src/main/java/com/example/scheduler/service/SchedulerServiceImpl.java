package com.example.scheduler.service;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.repository.SchedulerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Transient;
import java.util.List;



@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerServiceImpl(SchedulerRepository schedulerRepository) {
        this.schedulerRepository = schedulerRepository;
    }

    //스케줄러 저장
    @Override
    public SchedulerResponseDto saveScheduler(SchedulerRequestDto dto) {

        //요청받은 데이터로 Scheduler 객체 생성, ID 없음
        Scheduler scheduler = new Scheduler(dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents());

        //DB 저장
        return schedulerRepository.saveScheduler(scheduler);
    }

    //스케줄러 전건 조회
    @Override
    public List<SchedulerResponseDto> findAllScheduler() {

        return schedulerRepository.findAllScheduler();
    }

    //스케줄러 단건 조회
    @Override
    public SchedulerResponseDto findSchedulerById(Long id) {

        Scheduler scheduler = schedulerRepository.findSchedulerByIdOrElseThrow(id);

        return new SchedulerResponseDto(scheduler);
    }

    //스케줄러 업데이트(ID기반)
    @Transient
    @Override
    public SchedulerResponseDto updateScheduler(Long id, String title, String contents, String password) {

        if (title == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "타이틀과 컨텐츠의 값이 없습니다.");
        }

        checkingPassword(id, password);

        int updatedRow = schedulerRepository.updateScheduler(id, title, contents);

        if(updatedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다" + id);
        }

        Scheduler scheduler = schedulerRepository.findSchedulerByIdOrElseThrow(id);

        return new SchedulerResponseDto(scheduler);
    }

    //스케줄러 삭제(ID기반)
    @Override
    public void deleteScheduler(Long id, String password) {

        checkingPassword(id, password);

        int deletedRow = schedulerRepository.deleteScheduler(id);

        if(deletedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다" + id);
        }

    }

    //비밀번호 인증 로직
    @Override
    public void checkingPassword(Long id, String password) {
        Scheduler scheduler = schedulerRepository.findSchedulerById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없습니다: " + id));

        if(!scheduler.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다.");
        }
    }

}
