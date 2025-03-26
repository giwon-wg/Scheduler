package com.example.scheduler.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 2, max = 10, message = "이름은 2 ~ 10자 사이여야 합니다.")
    @Schema(description = "이름", example = "성이름")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    @Schema(description = "이메일", example = "email@email.con")
    private  String email;
}
