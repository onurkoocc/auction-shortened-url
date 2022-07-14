package com.tapu.auctionshortenedurl.Repository;

import com.tapu.auctionshortenedurl.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    Boolean existsByLongUrl(String longUrl);

    Boolean existsByShortUrl(String shortUrl);

    Url getByShortUrl(String shortUrl);

    Url getByLongUrl(String longUrl);
}
