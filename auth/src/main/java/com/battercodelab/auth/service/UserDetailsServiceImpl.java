package com.battercodelab.auth.service;

import com.battercodelab.auth.entity.Member;
import com.battercodelab.auth.entity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Can't find " + id));
        return new UserDetailsImpl(member);
    }
}
