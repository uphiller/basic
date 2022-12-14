package com.battercodelab.basic.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponse {

    private final String token;
    private final String username;
}
