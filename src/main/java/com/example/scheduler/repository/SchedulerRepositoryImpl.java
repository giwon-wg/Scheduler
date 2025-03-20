package com.example.scheduler.repository;


import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class SchedulerRepositoryImpl implements SchedulerRepository{

    //스케줄 리스트로 저장
    private final Map<Long, Scheduler> schedulerList = new HashMap<>();

    //스케줄러 저장
    @Override
    public Scheduler saveScheduler(Scheduler scheduler) {

        Long schedulerId = schedulerList.isEmpty() ? 1 : Collections.max(schedulerList.keySet()) + 1;
        scheduler.setId(schedulerId);

        schedulerList.put(schedulerId, scheduler);

        return scheduler;
    }

    //스케줄러 전건 조회
    @Override
    public List<SchedulerResponseDto> findAllScheduler() {

        //list 초기화
        List<SchedulerResponseDto> allScheduler = new ArrayList<>();

        for(Scheduler scheduler : schedulerList.values()){
            SchedulerResponseDto responseDto = new SchedulerResponseDto(scheduler);
            allScheduler.add(responseDto);
        }

        return allScheduler;
    }

    //스케줄러 단건 조회(ID기반)
    @Override
    public Scheduler findSchedulerById(Long id) {

        return schedulerList.get(id);
    }

    //스케줄러 삭제(ID기반)
    @Override
    public void deleteScheduler(Long id) {
        schedulerList.remove(id);
    }
}
