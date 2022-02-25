package com.ronsong.urlshortenerspring.controller;

import com.ronsong.urlshortenerspring.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ronsong
 */
@RestController
public class UrlController {
    @Autowired
    UrlService urlService;

    @GetMapping("/all")
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(urlService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> findByShortKey(@PathVariable("shortUrl") String shortUrl) {
        return new ResponseEntity<>(urlService.findByShortKey(shortUrl), HttpStatus.OK);
    }
}