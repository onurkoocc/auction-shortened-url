package com.tapu.auctionshortenedurl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Payload.Request.LoginRequest;
import com.tapu.auctionshortenedurl.Payload.Request.SignupRequest;
import com.tapu.auctionshortenedurl.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    void signupTest() throws Exception {
        SignupRequest user = new SignupRequest();
        user.setUsername("tapu1");
        user.setEmail("tapu1@tapu.com");
        user.setPassword("123456789");
        user.setRole("ADMIN");

        mockMvc.perform(post("/auth/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        User userEntity = userRepository.findByUsername("tapu1").get();
        assertThat(userEntity.getEmail()).isEqualTo("tapu1@tapu.com");
    }

    @Test
    void loginTest() throws Exception {
        LoginRequest user = new LoginRequest();
        user.setUsername("tapu");
        user.setPassword("123456789");

        mockMvc.perform(post("/auth/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

}