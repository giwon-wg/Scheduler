package com.example.scheduler.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "스케줄 제목", example = "20자 이하의 제목")
    private String title;

    @NotBlank(message = "내용은 필수값 입니다.")
    @Size(max = 200, message = "내용은 200자 이내여야 합니다.")
    @Schema(description = "스케줄 내용", example = "200자 이하의 내용")
    private String contents;

    @NotNull(message = "시작일은 필수값 입니다.")
    @Schema(description = "스케줄 시작일", example = "2025-03-25T14:00:00")
    private LocalDateTime startTime;

    @NotNull(message = "종료일은 필수값 입니다.")
    @Schema(description = "스케줄 종료일", example = "2025-03-25T14:00:00")
    private LocalDateTime endTime;

    @NotNull(message = "비밀번호는 필수값 입니다.")
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @NotBlank(message = "작성자 이름은 필수값 입니다.")
    @Schema(description = "스케줄 작성자명", example = "성이름")
    private String name;

    @Schema(description = "유저 아이디", example = "1")
    private Long userId;
}
