package com.example.scheduler.service;

import com.example.scheduler.DTO.SchedulerRequestDto;
import java.util.NoSuchElementException;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import com.example.scheduler.entity.User;
import com.example.scheduler.exception.PasswordException;
import com.example.scheduler.repository.SchedulerRepository;
import com.example.scheduler.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository schedulerRepository;
    private final UserRepository userRepository;

    public SchedulerServiceImpl(SchedulerRepository schedulerRepository, UserRepository userRepository) {
        this.schedulerRepository = schedulerRepository;
        this.userRepository = userRepository;
    }

    //스케줄러 저장
    @Override
    public SchedulerResponseDto saveScheduler(SchedulerRequestDto dto) {

        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 유저 ID 입니다.");
        }

        //요청받은 데이터로 Scheduler 객체 생성, ID 없음
        Scheduler scheduler = new Scheduler(dto.getTitle(), dto.getContents(), dto.getStartTime(), dto.getEndTime(), dto.getPassword(),dto.getName(), dto.getUserId());

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
    @Override
    public SchedulerResponseDto updateScheduler(Long id, String title, String contents, String name, String password) {

        if (title == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "타이틀과 컨텐츠의 값이 없습니다.");
        }

        checkingPassword(id, password);

        int updatedRow = schedulerRepository.updateScheduler(id, title, contents, name);

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
        Scheduler scheduler = schedulerRepository.findSchedulerById(id).orElseThrow(() -> new NoSuchElementException("스케줄을 찾을 수 없습니다: " + id));

        if(!scheduler.getPassword().equals(password)){
            throw new PasswordException("비밀번호가 틀립니다.");
        }
    }

    @Override
    public List<SchedulerResponseDto> searchSchedules(String name, LocalDate date, Integer months, Long userId) {
        LocalDate from = null;
        LocalDate to = LocalDate.now();

        if(months != null){
            from = to.minusMonths(months);
        } else if (date != null){
            from = date;
        }

        return schedulerRepository.searchByConditions(name, from, to, userId);
    }

    @Override
    public List<SchedulerResponseDto> getPaginationSchedules(int page, int size) {
        int offset = page * size;
        return schedulerRepository.findPaginationSchedules(offset, size);
    }
}
