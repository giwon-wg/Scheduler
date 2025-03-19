package com.example.scheduler.DTO;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerRequestDto {
    private String password;
    private String name;
    private String title;
    private String contents;

}
