package com.example.mockreskill.controller;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.model.entity.User;
import com.example.mockreskill.model.request.CreateUserRequest;
import com.example.mockreskill.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    ResponseEntity<List<User>> getAllUsers() {
        log.info(Constants.BEGIN_API + "getAllUsers");
        try {
            return ResponseEntity.ok().body(userService.getAllUsers());
        } finally {
            log.info(Constants.END_API + "getAllUsers");
        }
    }

    @PostMapping("/create")
    ResponseEntity<Void> CreateUser(@RequestBody CreateUserRequest userRequest) {
        log.info(Constants.BEGIN_API + "CreateUser");
        try {
            userService.CreateUser(userRequest);
            return ResponseEntity.ok().body(null);
        } finally {
            log.info(Constants.BEGIN_API + "CreateUser");
        }
    }
}
