package com.battercodelab.basic.controller;

import com.battercodelab.basic.service.UserDetailsImpl;
import com.battercodelab.basic.controller.dto.LoginDto;
import com.battercodelab.basic.controller.dto.SignupDto;
import com.battercodelab.basic.controller.mapper.MemberMapper;
import com.battercodelab.basic.entity.Member;
import com.battercodelab.basic.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final UserDetailsService userDetailsService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDto.Request request) {
        Member member = memberService.registerUser(request);
        SignupDto.Response response = memberMapper.toSignupResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDto.Request request) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getId());
        final String token = memberService.generateToken(userDetails.getUsername());
        LoginDto.Response response = memberMapper.toLoginResponseDto(((UserDetailsImpl) userDetails).getMember(), token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
