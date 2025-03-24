package com.example.scheduler.service;

import com.example.scheduler.DTO.UserRequestDto;
import com.example.scheduler.DTO.UserResponseDto;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = new User(null, dto.getUsername(), dto.getPassword(), dto.getEmail(), LocalDateTime.now());

        userRepository.save(user);

        User savedUser = userRepository.findByUsername(user.getUsername()).get();
        return new UserResponseDto(savedUser);
    }
}
