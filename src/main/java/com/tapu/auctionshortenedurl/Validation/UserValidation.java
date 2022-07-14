package com.tapu.auctionshortenedurl.Validation;

import com.tapu.auctionshortenedurl.Entity.Url;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Exception.UrlException;
import com.tapu.auctionshortenedurl.Exception.UserException;
import com.tapu.auctionshortenedurl.Payload.Request.LoginRequest;
import com.tapu.auctionshortenedurl.Payload.Request.SignupRequest;
import com.tapu.auctionshortenedurl.Repository.UrlRepository;
import com.tapu.auctionshortenedurl.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidation {
    UserRepository userRepository;
    UrlRepository urlRepository;

    public Boolean isValidSignup(SignupRequest signupRequest) throws UserException {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UserException("Error: Username is already exist. Please use a different username.");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserException("Error: Email is already exist. Please use a different e-mail address. ");
        }
        return true;
    }

    public Boolean isValidSignin(LoginRequest loginRequest) throws UserException {
        return true;
    }

    public Boolean isExistById(Integer userId) throws UserException {
        if (!userRepository.existsById(userId)) {
            throw new UserException("Error: User is not exist");
        }
        return true;
    }

    public Boolean isSuitableForAdd(Integer userId, String longUrl) throws UserException, UrlException {
        if (!userRepository.existsById(userId)) {
            throw new UserException("Error: User is not found");
        }
        User user = userRepository.getReferenceById(userId);
        Url url = new Url(longUrl);
        if (user.getUrls().contains(url)) {
            throw new UrlException("Error: This url already exist at this users urls");
        }
        return true;
    }

    public Boolean isHaveUrlByUserIdAndUrlId(Integer userId, Integer urlId) throws UserException, UrlException {
        if (!userRepository.existsById(userId)) {
            throw new UserException("Error: User is not found");
        }
        if (!urlRepository.existsById(urlId)) {
            throw new UrlException("Error: This url is not exist.");
        }
        User user = userRepository.getReferenceById(userId);
        Url url = urlRepository.getReferenceById(urlId);
        if (!user.getUrls().contains(url)) {
            throw new UrlException("Error: This url is not exist at this users urls");
        }
        return true;
    }

}
