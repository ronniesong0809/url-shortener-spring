package com.ronsong.urlshortenerspring.controller;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

/**
 * @author ronsong
 */
@RestController
public class UrlController {
    @Autowired
    UrlService urlService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(urlService.findAll());
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> findByShortKey(@PathVariable("shortUrl") String shortUrl) {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(urlService.findByShortKey(shortUrl).getLongUrl())).build();
    }

    @PostMapping("/shorten")
    public ResponseEntity<Object> shorten(@Validated @RequestBody ShortenDTO dto) {
        Url url = urlService.findByLongUrl(dto);

        if (ObjectUtils.isEmpty(url)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("url", urlService.shorten(dto).getShortUrl(), "message", "Shortened successfully"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("url", url.getShortUrl(), "message", "url already exists"));
    }
}