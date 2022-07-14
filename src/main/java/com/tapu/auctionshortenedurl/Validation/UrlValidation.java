package com.tapu.auctionshortenedurl.Validation;

import com.tapu.auctionshortenedurl.Exception.UrlException;
import com.tapu.auctionshortenedurl.Repository.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class UrlValidation {
    UrlRepository urlRepository;
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public boolean isValidUrl(String url) throws UrlException {
        Matcher m = URL_PATTERN.matcher(url);
        if (!m.matches()) {
            throw new UrlException("Error:URL is not valid. Please provide a valid URL");
        }
        return true;
    }

    public boolean isValidSave(String longUrl) throws UrlException {
        if (urlRepository.existsByLongUrl(longUrl)) {
            throw new UrlException("Error:URL is already exist. Please provide a different URL");
        }
        return true;
    }

    public boolean isValidDelete(Integer id) throws UrlException {
        if (!urlRepository.existsById(id)) {
            throw new UrlException("Error:Url is not exist. Please provide suitable URL id for delete");
        }
        return true;
    }

    public boolean isValidGet(String shortUrl) throws UrlException {
        if (!urlRepository.existsByShortUrl(shortUrl)) {
            throw new UrlException("Error:Url is not exist. Please provide suitable URL for redirect.");
        }
        return true;
    }
}
