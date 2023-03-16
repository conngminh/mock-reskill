package com.example.mockreskill.service;

import com.example.mockreskill.model.entity.User;
import com.example.mockreskill.model.request.CreateUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);

    void CreateUser(CreateUserRequest userRequest);

    List<User> getAllUsers();
}
