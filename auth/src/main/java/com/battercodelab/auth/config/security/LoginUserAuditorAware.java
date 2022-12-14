package com.battercodelab.auth.config.security;

import com.battercodelab.auth.entity.Member;
import com.battercodelab.auth.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginUserAuditorAware implements AuditorAware<Member> {

    @Override
    public Optional<Member> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member  = null;
        if(!principal.toString().equals("anonymousUser")) {
            member =((UserDetailsImpl) principal).getMember();
        } else {
            return null;
        }

        return Optional.ofNullable(member);
    }
}
