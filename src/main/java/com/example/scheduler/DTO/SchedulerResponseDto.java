package com.example.scheduler.DTO;

import com.example.scheduler.entity.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerResponseDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private String name;
    private Long userId;


    public SchedulerResponseDto(Scheduler scheduler){
        this.id = scheduler.getId();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.startTime = scheduler.getStartTime();
        this.endTime = scheduler.getEndTime();
        this.createdAt = scheduler.getCreatedAt();
        this.name = scheduler.getName();
        this.userId = scheduler.getUserId();
    }
}
