package com.battercodelab.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    @GetMapping(value = "/")
    public ResponseEntity<?> main() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<Mono<?>> foo(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return new ResponseEntity<>(Mono.just(principal), HttpStatus.OK);

    }
}
