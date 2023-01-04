package com.battercodelab.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(value = "/")
    public ResponseEntity<?> main() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/info")
    public ResponseEntity<?> hello(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
