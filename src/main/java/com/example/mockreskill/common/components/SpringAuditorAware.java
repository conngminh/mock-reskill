package com.example.mockreskill.common.components;

import com.example.mockreskill.model.config.AppUserDetail;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpringAuditorAware implements AuditorAware<String> {
    public SpringAuditorAware() {
    }
    public static Optional<String> getCurrentUserLogin() {
        AppUserDetail userDetail = (AppUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of((String) userDetail.getId().toString());
    }
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of((String)getCurrentUserLogin().orElse("anonymousUser"));
    }
}
