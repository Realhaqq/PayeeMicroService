package com.haqq.payee.controllers;


import com.haqq.payee.enums.RoleName;
import com.haqq.payee.pojos.LoginRequest;
import com.haqq.payee.pojos.SignUpRequest;
import com.haqq.payee.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.signin(loginRequest);
    }

    @PostMapping("/auth/user/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setRole(RoleName.ROLE_USER);
        return authService.userSignup(signUpRequest);
    }

    @PostMapping("/auth/creator/signup")
    public ResponseEntity<?> signupCreator(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setRole(RoleName.ROLE_CONTENT_CREATOR);
        return authService.userSignup(signUpRequest);
    }

}
