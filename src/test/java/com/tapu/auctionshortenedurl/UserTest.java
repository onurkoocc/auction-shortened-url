package com.tapu.auctionshortenedurl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapu.auctionshortenedurl.Entity.Url;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Repository.UrlRepository;
import com.tapu.auctionshortenedurl.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    @WithMockUser(username = "tapu", password = "123456789", roles = "ADMIN")
    @Transactional
    void createUrlTest() throws Exception {

        mockMvc.perform(post("/user/{userId}/url/create",3)
                        .contentType("application/json")
                        .content("www.tapu14.com"))
                .andExpect(status().isOk());

        User userEntity = userRepository.getReferenceById(3);
        Url urlEntity = urlRepository.getByLongUrl("www.tapu14.com");
        assertThat(urlEntity.getLongUrl()).isEqualTo("www.tapu14.com");
        assertThat(userEntity.getUrls().contains(urlEntity)).isTrue();
    }

    @Test
    @WithMockUser(username = "tapu", password = "123456789", roles = "ADMIN")
    void getUrlListTest() throws Exception {
        mockMvc.perform(get("/user/{userId}/url/list",3)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "tapu", password = "123456789", roles = "ADMIN")
    @Transactional
    void getUrlByUserIdAndUrlIdTest() throws Exception {
        mockMvc.perform(get("/user/{userId}/url/detail/{urlId}",3,30)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "tapu", password = "123456789", roles = "ADMIN")
    void removeUrlFromUsersUrlsTest() throws Exception {
        mockMvc.perform(delete("/user/{userId}/url/detail/{urlId}",3,33)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

}