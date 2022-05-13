package com.haqq.payee.controllers;

import com.haqq.payee.pojos.MakePaymentRequest;
import com.haqq.payee.pojos.Wallet;
import com.haqq.payee.security.CurrentUser;
import com.haqq.payee.security.UserPrincipal;
import com.haqq.payee.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_CONTENT_CREATOR')")
    @GetMapping("/creator/wallet")
    public Wallet getUserWallet(@CurrentUser UserPrincipal currentUser) {
        return userService.getUserWallet(currentUser);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/user/payment")
    public ResponseEntity<?> makePayment(@CurrentUser UserPrincipal currentUser, @RequestBody MakePaymentRequest request) {
        return userService.makePayment(currentUser, request);
    }
}
