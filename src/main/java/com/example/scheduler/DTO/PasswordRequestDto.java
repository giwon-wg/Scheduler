package com.example.scheduler.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequestDto {

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Schema(description = "비밀번호", example = "1234")
    private String password;
}
