package com.example.scheduler.repository;

import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SchedulerRepository {

    //스케줄러 저장
    SchedulerResponseDto saveScheduler(Scheduler scheduler);

    //스케줄러 전건 조회
    List<SchedulerResponseDto> findAllScheduler();

    //스케줄러 단건 조회
    Optional<Scheduler> findSchedulerById(Long id);

    Scheduler findSchedulerByIdOrElseThrow(Long id);

    int updateScheduler(Long id, String title, String contents, String name);

    //스케줄러 삭제
    int deleteScheduler(Long id);

    List<SchedulerResponseDto> searchByConditions(String name, LocalDate from, LocalDate to, Long userId);

    List<SchedulerResponseDto> findPaginationSchedules(int offset, int limit);

}
