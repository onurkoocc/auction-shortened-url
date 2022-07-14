package com.tapu.auctionshortenedurl.Payload.Response;

import lombok.Data;

@Data
public class ShortUrlResponse {
    private Integer id;
    private String shortUrl = "http://localhost:8080/url/s/";

    public ShortUrlResponse(Integer id, String shortUrl){
        this.id = id;
        this.shortUrl += shortUrl;
    }
}
