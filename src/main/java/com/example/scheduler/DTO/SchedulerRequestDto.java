package com.example.scheduler.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SchedulerRequestDto {

    @NotBlank(message = "제목은 필수값입니다.")
    @Size(max = 20, message = "제목은 20자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수값 입니다.")
    @Size(max = 200, message = "내용은 200자 이내여야 합니다.")
    private String contents;

    @NotNull(message = "시작일은 필수값 입니다.")
    private LocalDateTime startTime;

    @NotNull(message = "종료일은 필수값 입니다.")
    private LocalDateTime endTime;

    @NotNull(message = "비밀번호는 필수값 입니다.")
    private String password;

    @NotBlank(message = "작성자 이름은 필수값 입니다.")
    private String name;

    private Long userId;
}
