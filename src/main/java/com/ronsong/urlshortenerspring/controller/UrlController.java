package com.ronsong.urlshortenerspring.controller;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.UpdateDTO;
import com.ronsong.urlshortenerspring.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(urlService.findAll());
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<Object> findByShortKey(@PathVariable("shortKey") String shortKey) {
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(urlService.findByShortKey(shortKey).getLongUrl()))
                .build();
    }

    @PostMapping(path = "/shorten", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Object> shorten(@Validated ShortenDTO dto) {
        if (Boolean.FALSE.equals(urlService.exists(dto))) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("url", urlService.shorten(dto).getShortUrl(), "message", "Shortened successfully"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("url", urlService.updateByLongUrl(dto).getShortUrl(), "message", "Url already exists"));
    }

    @PutMapping(path = "/{shortKey}", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Object> update(@PathVariable("shortKey") String shortKey, @Validated UpdateDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("url", urlService.updateByShortKey(shortKey, dto), "message", "Updated successfully"));
    }
}