package com.example.scheduler.DTO;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerResponseDto {
    private Long id;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime time;
    private LocalDateTime editTime;
}
