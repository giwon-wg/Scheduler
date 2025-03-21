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
    private String password;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime time;


    public SchedulerResponseDto(Scheduler scheduler){
        this.id = scheduler.getId();
        this.password = scheduler.getPassword();
        this.name = scheduler.getName();
        this.title = scheduler.getTitle();
        this.contents = scheduler.getContents();
        this.time = scheduler.getTime();
    }
}
