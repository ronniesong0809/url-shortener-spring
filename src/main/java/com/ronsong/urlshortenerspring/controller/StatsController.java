package com.ronsong.urlshortenerspring.controller;

import com.ronsong.urlshortenerspring.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ronsong
 */
@RestController
public class StatsController {
    @Autowired
    StatsService statsService;

    @GetMapping("/all/stats")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statsService.findAll());
    }

    @GetMapping("/{shortKey}/stats")
    public ResponseEntity<Object> findByShortKey(@PathVariable("shortKey") String shortKey) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("url", statsService.findByShortKey(shortKey)));
    }
}