package com.tapu.auctionshortenedurl.Controller;

import com.tapu.auctionshortenedurl.Exception.UrlException;
import com.tapu.auctionshortenedurl.Service.UrlService;
import com.tapu.auctionshortenedurl.Validation.UrlValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/url")
@AllArgsConstructor
public class UrlController {
    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);
    UrlValidation urlValidation;
    UrlService urlService;

    @GetMapping("/s/{shortUrl}")
    public ResponseEntity<?> redirectToUrl(@PathVariable("shortUrl") String shortUrl) {
        logger.info("Entered redirectToUrl");
        try {
            urlValidation.isValidGet(shortUrl);
        } catch (UrlException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(301).location(URI.create(urlService.getLongUrlByShortUrl(shortUrl))).build();
        //return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlService.getLongUrlByShortUrl(shortUrl))).build();
    }
}
