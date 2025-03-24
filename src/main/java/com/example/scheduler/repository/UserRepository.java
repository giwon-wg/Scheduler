package com.example.scheduler.repository;

import com.example.scheduler.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void save(User user);
}
