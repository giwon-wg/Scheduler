package com.example.scheduler.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SchedulerRequestDto {
    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String password;
    private String name;
    private Long userId;
}
