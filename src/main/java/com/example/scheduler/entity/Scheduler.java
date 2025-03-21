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

    //스케줄러 값 받기
    public Scheduler(String password, String name, String title, String contents){
        this.password = password;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.time = LocalDateTime.now();
    }

    //스케줄 수정
    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.time = LocalDateTime.now();
    }

}