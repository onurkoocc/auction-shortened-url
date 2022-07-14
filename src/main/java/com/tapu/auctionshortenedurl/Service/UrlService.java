package com.tapu.auctionshortenedurl.Service;

import com.tapu.auctionshortenedurl.Entity.Url;

public interface UrlService {
    String getLongUrlByShortUrl(String shortUrl);

    Url getUrlById(Integer urlId);

    void deleteUrlById(Integer urlId);
}
