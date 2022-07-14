package com.tapu.auctionshortenedurl;

import com.tapu.auctionshortenedurl.Controller.AuthController;
import com.tapu.auctionshortenedurl.Controller.UrlController;
import com.tapu.auctionshortenedurl.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private AuthController authController;
    @Autowired
    private UrlController urlController;
    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
        assertThat(urlController).isNotNull();
        assertThat(userController).isNotNull();
    }
}