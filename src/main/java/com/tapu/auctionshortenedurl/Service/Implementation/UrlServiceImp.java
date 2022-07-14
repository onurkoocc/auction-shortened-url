package com.tapu.auctionshortenedurl.Service.Implementation;

import com.tapu.auctionshortenedurl.Entity.Url;
import com.tapu.auctionshortenedurl.Repository.UrlRepository;
import com.tapu.auctionshortenedurl.Service.UrlService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class UrlServiceImp implements UrlService {
    UrlRepository urlRepository;

    public String getLongUrlByShortUrl(String shortUrl) {
        return urlRepository.getByShortUrl(shortUrl).getLongUrl();
    }

    public Url getUrlById(Integer urlId) {
        return urlRepository.getReferenceById(urlId);
    }

    public void deleteUrlById(Integer urlId) {
        urlRepository.deleteById(urlId);
    }
}
