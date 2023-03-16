package com.example.mockreskill.controller;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.common.contants.MessageCode;
import com.example.mockreskill.common.exeption.BadRequestException;
import com.example.mockreskill.model.config.AppUserDetail;
import com.example.mockreskill.model.request.LoginRequest;
import com.example.mockreskill.model.response.LoginResponse;
import com.example.mockreskill.security.JWTUtils;
import com.example.mockreskill.service.impl.AuthServiceImpl;
import com.example.mockreskill.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final RequestValidator<Object> requestValidator;
    @GetMapping("/home")
    ResponseEntity<String> Home() {
        log.info(Constants.BEGIN_API + "Home");
        try {
            return ResponseEntity.ok().body("Welcome to my channel!");
        } finally {
            log.info(Constants.END_API + "Home");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        log.info(Constants.BEGIN_API + "authenticateUser");
        try {
            requestValidator.validate(loginRequest);
            return ResponseEntity.ok().body(authService.login(loginRequest));
        } finally{
            log.info(Constants.END_API + "authenticateUser");
        }
    }
}
