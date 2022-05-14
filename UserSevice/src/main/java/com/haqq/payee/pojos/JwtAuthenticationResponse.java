package com.haqq.payee.pojos;


import com.haqq.payee.entities.User;

public class JwtAuthenticationResponse {
    private String accessToken;
    private int expiresIn = 180000;
    private String tokenType = "Bearer";
    private User user;

    public JwtAuthenticationResponse(String accessToken, int expiresIn, User user) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.user = user;


    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
