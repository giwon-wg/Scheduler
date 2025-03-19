package com.example.scheduler.DTO;

import com.example.scheduler.entity.Scheduler;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String password;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime time;
//    private LocalDateTime editTime;

    public SchedulerResponseDto(Scheduler scheduler){
        this.id = scheduler.getId();
        this.password = scheduler.getPassword();
        this.name = scheduler.getName();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.time = scheduler.getTime();
    }
}
