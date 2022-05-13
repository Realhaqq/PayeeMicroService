package com.haqq.payee.services;

import com.haqq.payee.entities.Role;
import com.haqq.payee.entities.User;
import com.haqq.payee.entities.Wallet;
import com.haqq.payee.enums.RoleName;
import com.haqq.payee.errors.AppException;
import com.haqq.payee.pojos.ApiResponse;
import com.haqq.payee.pojos.JwtAuthenticationResponse;
import com.haqq.payee.pojos.LoginRequest;
import com.haqq.payee.pojos.SignUpRequest;
import com.haqq.payee.repositories.RoleRepository;
import com.haqq.payee.repositories.UserRepository;
import com.haqq.payee.repositories.WalletRepository;
import com.haqq.payee.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
    public ResponseEntity<?> signin(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, 180000));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid Credentials", 101, null));

        }

    }

    public ResponseEntity<?> userSignup(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail()))
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!", 101, null),
                    HttpStatus.BAD_REQUEST);


        if(userRepository.existsByEmail(signUpRequest.getUsername()))
            return new ResponseEntity(new ApiResponse(false, "Username already in use!", 101, null),
                    HttpStatus.BAD_REQUEST);



        String uuid = UUID.randomUUID().toString();

        // Creating user's account
        User user = new User();

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));


        Role userRole = roleRepository.findByName(signUpRequest.getRole())
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setFullName(signUpRequest.getFullName());
        user.setEmail(signUpRequest.getEmail());
        user.setUuid(uuid);
        user.setWalletId(CreateUserWallet(uuid));
        user.setUsername(signUpRequest.getUsername());


        try {
            User result = userRepository.save(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/users/{username}")
                    .buildAndExpand(result.getUsername()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully", 000, result));

        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(new ApiResponse(false, "Error Occured!", 101, null), HttpStatus.BAD_REQUEST);
        }

    }

    private String CreateUserWallet(String uuid) {
        String wallet_id = String.valueOf(System.currentTimeMillis());

        Wallet wallet = new Wallet();
        wallet.setWalletId(wallet_id);
        wallet.setUserId(uuid);
        walletRepository.save(wallet);
        System.out.println("Wallet Created!");
        return wallet_id;
    }
}
