package com.example.mockreskill.service.impl;

import com.example.mockreskill.common.contants.Constants;
import com.example.mockreskill.model.config.AppUserDetail;
import com.example.mockreskill.model.entity.User;
import com.example.mockreskill.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(Constants.BEGIN_SERVICE + "loadUserByUsername");
        try {
            Optional<User> userOpt = userRepository.getUserByUsername(username);
            if (!userOpt.isPresent()) {
                log.error("loadUserByUsername: User not found in the database: {}", username);
                throw new UsernameNotFoundException("User not found");
            }
            User user = userOpt.get();
            Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRoleId()));
            AppUserDetail userDetail = new AppUserDetail(user.getId(),user.getUsername(), user.getPassword(), authorities);
            return userDetail;
        } finally {
            log.info(Constants.END_SERVICE + "loadUserByUsername");
        }
    }
}
