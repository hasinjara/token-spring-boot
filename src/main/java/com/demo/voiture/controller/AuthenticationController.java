package com.demo.token.controller;


import com.demo.token.dto.JwtAuthenticationResponse;
import com.demo.token.dto.SignInRequest;
import com.demo.token.dto.SignUpRequest;
import com.demo.token.models.Retour;
import com.demo.token.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public Retour signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public Retour signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }

}
