package com.example.mockreskill.service.impl;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.model.entity.User;
import com.example.mockreskill.model.request.CreateUserRequest;
import com.example.mockreskill.repository.UserRepository;
import com.example.mockreskill.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;
    @Override
    public Optional<User> getUserByUsername(String username) {
        return null;
    }

    @Override
    public void CreateUser(CreateUserRequest userRequest) {
        log.info(Constants.BEGIN_SERVICE + "CreateUser");
        try {
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setUsername(userRequest.getUsername());

            user.setPassword(userRequest.getPassword());
            user.setName(userRequest.getName());
            user.setRoleId(userRequest.getRoleId());
            userRepository.save(user);
        } finally {
            log.info(Constants.END_SERVICE + "CreateUser");
        }
    }

    @Override
    public List<User> getAllUsers() {
        log.info(Constants.BEGIN_SERVICE + "getAllUsers");
        try {
            return userRepository.findAll();
        } finally {
            log.info(Constants.END_API + "getAllUsers");
        }
    }
}
