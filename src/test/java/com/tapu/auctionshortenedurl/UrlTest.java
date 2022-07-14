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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UrlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "tapu", password = "123456789", roles = "ADMIN")
    void redirectToUrlTest() throws Exception {

        mockMvc.perform(get("/url/s/{shortUrl}", "+UZAMNJC")
                        .contentType("application/json"))
                .andExpect(status().is3xxRedirection());
    }
}
