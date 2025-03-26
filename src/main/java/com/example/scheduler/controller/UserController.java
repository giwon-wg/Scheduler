package com.example.scheduler.controller;

import com.example.scheduler.DTO.UserRequestDto;
import com.example.scheduler.DTO.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.scheduler.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "유저 등록", description = "유저를 등록합니다.")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid UserRequestDto dto) {
        UserResponseDto response = userService.registerUser(dto);
        return ResponseEntity.ok(response);
    }
}
