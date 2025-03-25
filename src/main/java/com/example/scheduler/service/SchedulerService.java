package com.example.scheduler.service;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface SchedulerService {

    //스케줄러 저장
    SchedulerResponseDto saveScheduler(SchedulerRequestDto dto);

    //스케줄러 전건 조회
    List<SchedulerResponseDto> findAllScheduler();

    //스케줄러 단건 조회(ID기반)
    SchedulerResponseDto findSchedulerById(Long id);

    //스케줄러 업데이트
    SchedulerResponseDto updateScheduler(Long id, String title, String contents, String name, String password);

    //스케줄러 삭제(ID기반)
    void deleteScheduler(Long id, String password);

    //비밀번호 인증 로직
    void checkingPassword(Long id, String password);

    List<SchedulerResponseDto> searchSchedules(String name, LocalDate date, Integer months, Long userId);

    //페이지네이션
    List<SchedulerResponseDto> getPaginationSchedules(int page, int size);
}
