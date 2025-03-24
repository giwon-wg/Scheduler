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

    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private String password;
    private String name;

    //일정 생성용 생성자
    public Scheduler(String title, String contents, LocalDateTime startTime, LocalDateTime endTime, String password, String name){
        this.title = title;
        this.contents = contents;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = LocalDateTime.now();
        this.password = password;
        this.name = name;
    }

    //스케줄 수정
    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
    }

}