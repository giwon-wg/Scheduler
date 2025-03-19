package com.example.scheduler.controller;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
import com.example.scheduler.entity.Scheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    //inmemory에 스케줄 리스트로 저장
    private final Map<Long, Scheduler> schedulerList = new HashMap<>();

    @PostMapping
    public SchedulerResponseDto createScheduler(@RequestBody SchedulerRequestDto dto){

        // id 1씩 증가
        Long schedulerId = schedulerList.isEmpty() ? 1 : Collections.max(schedulerList.keySet()) + 1;

        // 요청받은 데이터에서 스케줄 생성
        Scheduler scheduler = new Scheduler(schedulerId, dto.getPassword(), dto.getName(), dto.getTitle(), dto.getContents(), LocalDateTime.now());

        // inmemory DB에 저장
        schedulerList.put(schedulerId, scheduler);

        return new SchedulerResponseDto(scheduler);
    }

    //ID 기준으로 스케줄 검색
    @GetMapping("/{id}")
    public SchedulerResponseDto findSchedulerById(@PathVariable Long id){
        Scheduler scheduler = schedulerList.get(id);

        return new SchedulerResponseDto(scheduler);
    }

    //ID 기준으로 스케줄 수정
    @PutMapping("/{id}")
    public SchedulerResponseDto updateSchedulerById(
            @PathVariable Long id,
            @RequestBody SchedulerRequestDto dto
    ){
        Scheduler scheduler = schedulerList.get(id);

        scheduler.update(dto);

        return new SchedulerResponseDto(scheduler);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduler(@PathVariable Long id){
        //삭제 로직(id 기준)
        schedulerList.remove(id);

        return ResponseEntity.ok("스케줄이 삭제되었습니다.");
    }
}
