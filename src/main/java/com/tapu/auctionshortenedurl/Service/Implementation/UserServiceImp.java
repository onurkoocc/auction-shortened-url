package com.tapu.auctionshortenedurl.Service.Implementation;

import com.tapu.auctionshortenedurl.Entity.Url;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Payload.Response.ShortUrlResponse;
import com.tapu.auctionshortenedurl.Repository.UrlRepository;
import com.tapu.auctionshortenedurl.Repository.UserRepository;
import com.tapu.auctionshortenedurl.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    UrlRepository urlRepository;

    public User getUserById(Integer userID) {
        return userRepository.getReferenceById(userID);
    }

    public ShortUrlResponse addUrlToUsersUrls(Integer userId, String longUrl) {
        User user = userRepository.getReferenceById(userId);
        Set<Url> urls = user.getUrls();
        Url url;
        if (urlRepository.existsByLongUrl(longUrl)) {
            url = urlRepository.getByLongUrl(longUrl);
        } else {
            url = urlRepository.save(new Url(longUrl));
        }
        urls.add(url);
        user.setUrls(urls);
        userRepository.save(user);
        return new ShortUrlResponse(url.getId(), url.getShortUrl());
    }

    public Set<Url> getUrlListByUserId(Integer userId) {
        User user = userRepository.getReferenceById(userId);
        return user.getUrls();
    }

    public void removeUrlFromUsersUrlsByUserIdAndUrlId(Integer userId, Integer urlId) {
        User user = userRepository.getReferenceById(userId);
        Url url = urlRepository.getReferenceById(urlId);
        Set<Url> urls = user.getUrls();
        urls.remove(url);
        user.setUrls(urls);
        userRepository.save(user);
    }
}
