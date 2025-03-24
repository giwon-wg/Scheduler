package com.example.scheduler.service;

import com.example.scheduler.DTO.UserRequestDto;
import com.example.scheduler.DTO.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRequestDto dto);

}
