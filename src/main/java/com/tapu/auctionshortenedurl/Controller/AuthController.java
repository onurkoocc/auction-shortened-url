package com.tapu.auctionshortenedurl.Controller;


import com.tapu.auctionshortenedurl.Exception.RoleException;
import com.tapu.auctionshortenedurl.Exception.UserException;
import com.tapu.auctionshortenedurl.Payload.Request.LoginRequest;
import com.tapu.auctionshortenedurl.Payload.Request.SignupRequest;
import com.tapu.auctionshortenedurl.Service.AuthService;
import com.tapu.auctionshortenedurl.Validation.RoleValidation;
import com.tapu.auctionshortenedurl.Validation.UserValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    AuthService authService;
    UserValidation userValidation;
    RoleValidation roleValidation;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Entered authenticateUser");
        try {
            userValidation.isValidSignin(loginRequest);
        } catch (UserException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        logger.info("Entered registerUser");
        try {
            userValidation.isValidSignup(signUpRequest);
            roleValidation.isExist(signUpRequest.getRole());
        } catch (UserException | RoleException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

}
