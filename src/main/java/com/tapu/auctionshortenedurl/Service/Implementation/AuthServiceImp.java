package com.tapu.auctionshortenedurl.Service.Implementation;

import com.tapu.auctionshortenedurl.Entity.ERole;
import com.tapu.auctionshortenedurl.Entity.Role;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Payload.Request.LoginRequest;
import com.tapu.auctionshortenedurl.Payload.Request.SignupRequest;
import com.tapu.auctionshortenedurl.Payload.Response.JwtResponse;
import com.tapu.auctionshortenedurl.Repository.RoleRepository;
import com.tapu.auctionshortenedurl.Repository.UserRepository;
import com.tapu.auctionshortenedurl.Security.Jwt.JwtUtils;
import com.tapu.auctionshortenedurl.Security.Service.UserDetailsImpl;
import com.tapu.auctionshortenedurl.Service.AuthService;
import com.tapu.auctionshortenedurl.Service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;
    RoleService roleService;

    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }


    public Boolean registerUser(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();
        Role role;
        if (strRole.equalsIgnoreCase("admin")) {
            role = roleService.getRoleByName(ERole.ADMIN);
        } else {
            role = roleService.getRoleByName(ERole.USER);
        }
        user.setRole(role);
        userRepository.save(user);

        return true;
    }
}
