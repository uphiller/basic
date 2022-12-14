package com.battercodelab.auth.service;

import com.battercodelab.auth.controller.dto.SignupDto;
import com.battercodelab.auth.entity.Member;
import com.battercodelab.auth.entity.MemberRole;
import com.battercodelab.auth.entity.repository.MemberRepository;
import com.battercodelab.core.exception.BusinessException;
import com.battercodelab.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder encoder;

    public Member registerUser(SignupDto.Request requestDto) {
        String id = requestDto.getId();
        Optional<Member> existUser = memberRepository.findById(id);
        if(existUser.isPresent()) throw new BusinessException(ErrorCode.conflict);
        String password = passwordEncoder.encode(requestDto.getPassword());
        MemberRole role = MemberRole.USER;
        Member member = new Member(id, password, requestDto.getName(), role, this.generateToken(id));
        memberRepository.save(member);

        return member;
    }

    public String generateToken(String id){
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        /*String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));*/
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(id)
//                .claim("scope", scope)
                .build();
        // @formatter:on
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
