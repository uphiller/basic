package com.battercodelab.auth.controller.mapper;

import com.battercodelab.auth.controller.dto.LoginDto;
import com.battercodelab.auth.controller.dto.SignupDto;
import com.battercodelab.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "token", target = "token"),
    })
    SignupDto.Response toSignupResponseDto(Member member);

    @Mappings({
            @Mapping(source = "member.id", target = "id"),
            @Mapping(source = "member.name", target = "name"),
            @Mapping(source = "token", target = "token"),
    })
    LoginDto.Response toLoginResponseDto(Member member, String token);
}
