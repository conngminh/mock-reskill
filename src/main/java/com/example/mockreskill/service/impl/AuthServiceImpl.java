package com.example.mockreskill.service.impl;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.common.contants.MessageCode;
import com.example.mockreskill.common.exeption.BadRequestException;
import com.example.mockreskill.model.config.AppUserDetail;
import com.example.mockreskill.model.request.LoginRequest;
import com.example.mockreskill.model.response.LoginResponse;
import com.example.mockreskill.repository.UserRepository;
import com.example.mockreskill.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public LoginResponse login(LoginRequest request) {
        log.info(Constants.BEGIN_SERVICE + "login");
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);
            AppUserDetail user = (AppUserDetail) authenticate.getPrincipal();
            String jwt = jwtUtils.generateToken(user);
            return new LoginResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(MessageCode.USER_NOT_FOUND);
        }
        finally {
            log.info(Constants.END_SERVICE + "login");
        }
    }
}
