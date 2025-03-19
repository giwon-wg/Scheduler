package com.example.scheduler.controller;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private final Map<Long, Scheduler> schedulerList = new HashMap<>();

    @PostMapping
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto dto){

        // id 1씩 증가 필요
        Long schedulerId = schedulerList.isEmpty() ? 1 : Collections.max(schedulerList.keySet()) + 1;

        // 요청받은 데이터에서 스케줄 생성
        Scheduler scheduler = new Scheduler(schedulerId, dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents(), LocalDateTime.now());

        // inmemory DB에 저장
        schedulerList.put(schedulerId, scheduler);

        return new SchedulerResponseDto(scheduler);
    }

    @GetMapping("/{id}")
    public SchedulerResponseDto findSchedulerById(@PathVariable Long id){
        Scheduler scheduler = schedulerList.get(id);

        return new SchedulerResponseDto(scheduler);
    }
}
