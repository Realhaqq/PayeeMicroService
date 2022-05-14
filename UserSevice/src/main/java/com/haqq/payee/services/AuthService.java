package com.haqq.payee.services;

import com.haqq.payee.apihandlers.WalletServiceApiHandler;
import com.haqq.payee.entities.Role;
import com.haqq.payee.entities.User;
import com.haqq.payee.errors.AppException;
import com.haqq.payee.pojos.*;
import com.haqq.payee.repositories.RoleRepository;
import com.haqq.payee.repositories.UserRepository;
import com.haqq.payee.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import retrofit2.Response;

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
    JwtTokenProvider tokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WalletServiceApiHandler walletServiceApiHandler;

    Logger logger = LoggerFactory.getLogger(AuthService.class);

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
            User user = userRepository.findByEmail(loginRequest.getEmail()).get();
            return ResponseEntity.status(HttpStatus.OK).body(new JwtAuthenticationResponse(jwt, 180000, user));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "Invalid Credentials", 101, null));

        }

    }

    public ResponseEntity<?> userSignup(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail()))
        return ResponseEntity.status(HttpStatus.IM_USED).body(new ApiResponse(false, "Email Address already in use!", 101, null));



        if(userRepository.existsByEmail(signUpRequest.getUsername()))
        return ResponseEntity.status(HttpStatus.IM_USED).body(new ApiResponse(false, "Username already in use!", 101, null));




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
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User registered successfully", 101, null));


        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Error Occured!", 101, null));
        }

    }

    private String CreateUserWallet(String uuid) {
        String wallet_id = String.valueOf(System.currentTimeMillis());

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUuid(uuid);
        createWalletRequest.setWalletId(wallet_id);
        try {
            Response response = walletServiceApiHandler.createWallet(createWalletRequest);
            logger.info("Wallet Created Successfully" + response.body());
            if(response.isSuccessful()) {
                return wallet_id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Wallet Creation Failed" + e.getMessage());
            return null;
        }

        return null;
    }
}
