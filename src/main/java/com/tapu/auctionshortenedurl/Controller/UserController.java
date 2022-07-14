package com.tapu.auctionshortenedurl.Controller;

import com.tapu.auctionshortenedurl.Exception.UrlException;
import com.tapu.auctionshortenedurl.Exception.UserException;
import com.tapu.auctionshortenedurl.Service.UrlService;
import com.tapu.auctionshortenedurl.Service.UserService;
import com.tapu.auctionshortenedurl.Validation.UrlValidation;
import com.tapu.auctionshortenedurl.Validation.UserValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService;
    UrlService urlService;
    UserValidation userValidation;
    UrlValidation urlValidation;

    @PostMapping("/{userId}/url/create")
    public ResponseEntity<?> createUrl(@PathVariable("userId") Integer userId, @RequestBody String longUrl) {
        logger.info("Entered createUrl");
        try {
            urlValidation.isValidUrl(longUrl);
            userValidation.isSuitableForAdd(userId, longUrl);
        } catch (UserException | UrlException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userService.addUrlToUsersUrls(userId, longUrl));
    }

    @GetMapping("/{userId}/url/list")
    public ResponseEntity<?> getUrlList(@PathVariable("userId") Integer userId) {
        logger.info("Entered getUrlList");
        try {
            userValidation.isExistById(userId);
        } catch (UserException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(userService.getUrlListByUserId(userId));
    }

    @GetMapping("/{userId}/url/detail/{urlId}")
    public ResponseEntity<?> getUrlByUserIdAndUrlId(@PathVariable("userId") Integer userId,
                                                    @PathVariable("urlId") Integer urlId) {
        logger.info("Entered getUrlByUserIdAndUrlId");
        try {
            userValidation.isHaveUrlByUserIdAndUrlId(userId, urlId);
        } catch (UserException | UrlException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(urlService.getUrlById(urlId));
    }

    @DeleteMapping("/{userId}/url/detail/{urlId}")
    public ResponseEntity<?> removeUrlFromUsersUrls(@PathVariable("userId") Integer userId,
                                                    @PathVariable("urlId") Integer urlId) {
        logger.info("Entered removeUrlFromUsersUrls");
        try {
            userValidation.isHaveUrlByUserIdAndUrlId(userId, urlId);
        } catch (UserException | UrlException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        userService.removeUrlFromUsersUrlsByUserIdAndUrlId(userId, urlId);
        return ResponseEntity.ok("Url removed from users url list.");
    }

}
