package com.tapu.auctionshortenedurl.Entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String longUrl;
    private String shortUrl;

    public Url(String longUrl) {
        this.longUrl = longUrl;
        generateShortUrl(longUrl);
    }

    public Url() {

    }

    private void generateShortUrl(String s) {
        String md5Hex = DigestUtils.md5Hex(s);
        long hashcode = md5Hex.toLowerCase().hashCode();
        int alphabethStart = 'A';
        StringBuilder result = new StringBuilder();
        if (hashcode < 0) {
            hashcode = Math.abs(hashcode);
            result.append("+");
        }
        while (hashcode > 26) {
            char letter = (char) (alphabethStart + (hashcode % 26));
            result.append(letter);
            hashcode /= 26;
        }
        result.append((char) (alphabethStart + hashcode));
        this.shortUrl = result.toString();
    }

    public Integer getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Url)) return false;
        Url url = (Url) o;
        return getLongUrl().equals(url.getLongUrl()) && getShortUrl().equals(url.getShortUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongUrl(), getShortUrl());
    }
}
