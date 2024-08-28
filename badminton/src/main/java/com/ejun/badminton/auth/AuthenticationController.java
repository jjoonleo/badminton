package com.ejun.badminton.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(
           @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.SignUp(request));
    }
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.signIn(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}