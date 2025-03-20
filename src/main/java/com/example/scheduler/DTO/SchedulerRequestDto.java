package com.example.scheduler.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SchedulerRequestDto {
    private String password;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime time;
}
