package com.haqq.payee.controllers;

import com.haqq.payee.enums.RoleName;
import com.haqq.payee.pojos.ApiResponse;
import com.haqq.payee.pojos.LoginRequest;
import com.haqq.payee.pojos.LoginResponse;
import com.haqq.payee.pojos.SignUpRequest;
import com.haqq.payee.services.AuthService;
import com.haqq.payee.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

@CrossOrigin
@Controller
@Slf4j
@Validated
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        log.info("Login attempt with email: {}", email);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        try {
            ResponseEntity<?> response = authService.signin(loginRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Login successful" + response.getBody());
                model.addAttribute("loginResponse", response.getBody());
                return "/dashboard";
            }else {
                model.addAttribute("error", "Invalid credentials");
                return "/login";
            }
        } catch (Exception e) {
            log.error("Error while logging in: {}", e.getMessage());
            model.addAttribute("error", "Error while logging in");
            return "/login";
        }
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/user/register")
    public String signup(@RequestParam("fullName") String fullName, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role,  Model model) {
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setFullName(fullName);
        signupRequest.setUsername(username);
        signupRequest.setEmail(email);
        signupRequest.setPassword(password);
        signupRequest.setRole(RoleName.valueOf(role));

        try {
            ResponseEntity<ApiResponse> response = (ResponseEntity<ApiResponse>) authService.userSignup(signupRequest);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Signup successful" + response.getBody());
                model.addAttribute("signupResponse", response.getBody().getMessage());

                if (response.getBody().getSuccess() == true) {
                    return "login";
                }else {
                    return "register";
                }
            }
        } catch (Exception e) {
            log.error("Error while signing up: {}", e.getMessage());
            model.addAttribute("error", "Error while signing up");
        }
        return "login";
    }



}
