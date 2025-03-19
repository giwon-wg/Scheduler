package com.example.scheduler.entity;

import com.example.scheduler.DTO.SchedulerRequestDto;
import com.example.scheduler.DTO.SchedulerResponseDto;
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

    //스케줄 수정
    public void update(SchedulerRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.time = LocalDateTime.now();
    }
}
