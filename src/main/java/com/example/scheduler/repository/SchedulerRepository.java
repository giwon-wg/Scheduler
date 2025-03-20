package com.example.scheduler.repository;

import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;

import java.util.List;

public interface SchedulerRepository {

    //스케줄러 저장
    Scheduler saveScheduler(Scheduler scheduler);

    //스케줄러 전건 조회
    List<SchedulerResponseDto> findAllScheduler();

    //스케줄러 단건 조회
    Scheduler findSchedulerById(Long id);

    //스케줄러 삭제
    void deleteScheduler(Long id);
}
