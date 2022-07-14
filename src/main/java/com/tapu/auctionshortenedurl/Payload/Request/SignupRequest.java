package com.tapu.auctionshortenedurl.Payload.Request;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String role = "";
    private String password;
}