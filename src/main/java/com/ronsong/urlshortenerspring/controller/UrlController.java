package com.ronsong.urlshortenerspring.controller;

import com.ronsong.urlshortenerspring.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}