package com.tapu.auctionshortenedurl.Service;

import com.tapu.auctionshortenedurl.Entity.Url;
import com.tapu.auctionshortenedurl.Entity.User;
import com.tapu.auctionshortenedurl.Payload.Response.ShortUrlResponse;

import java.util.Set;

public interface UserService {
    User getUserById(Integer userID);

    ShortUrlResponse addUrlToUsersUrls(Integer userId, String longUrl);

    Set<Url> getUrlListByUserId(Integer userId);

    void removeUrlFromUsersUrlsByUserIdAndUrlId(Integer userId, Integer urlId);
}
