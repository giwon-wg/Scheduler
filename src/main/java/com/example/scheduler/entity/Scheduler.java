package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Scheduler {
    private Long id;
    private String password;
    private String name;
    private String title;
    private String contents;
    private LocalDateTime time;
//    private LocalDateTime  editTime;
}
