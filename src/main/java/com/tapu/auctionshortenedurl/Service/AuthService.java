package com.tapu.auctionshortenedurl.Service;

import com.tapu.auctionshortenedurl.Payload.Request.LoginRequest;
import com.tapu.auctionshortenedurl.Payload.Request.SignupRequest;
import com.tapu.auctionshortenedurl.Payload.Response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    Boolean registerUser(SignupRequest signUpRequest);
}
