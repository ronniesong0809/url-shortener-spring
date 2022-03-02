package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.UpdateDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.repository.UrlRepository;
import com.ronsong.urlshortenerspring.utils.EncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * @author ronsong
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Value("${BASE_URL}")
    String baseUrl;

    @Override
    public Collection<Url> findAll() {
        return urlRepository.findAll();
    }

    @Override
    public Url findByShortKey(String shortKey) {
        return urlRepository.findByShortKey(shortKey);
    }

    @Override
    public Url findByLongUrl(String longUrl) {
        return urlRepository.findByLongUrl(longUrl);
    }

    @Override
    public Url shorten(ShortenDTO dto) {
        String shortKey = EncodeUtils.encode(dto.getUrl());

        Url url = Url.builder()
                .longUrl(dto.getUrl())
                .shortKey(shortKey)
                .shortUrl(baseUrl + '/' + shortKey)
                .createdAt(new Date())
                .updatedAt(new Date())
                .expiration(dto.getExpiration() == null ? 0 : dto.getExpiration())
                .build();
        urlRepository.save(url);
        return url;
    }

    @Override
    public Boolean exists(ShortenDTO dto) {
        return urlRepository.existsByLongUrl(dto.getUrl());
    }

    @Override
    public Url updateByLongUrl(ShortenDTO dto) {
        Url url = findByLongUrl(dto.getUrl());
        url.setExpiration(dto.getExpiration());
        return urlRepository.save(url);
    }

    @Override
    public Url updateByShortKey(String shortKey, UpdateDTO dto) {
        Url url = findByShortKey(shortKey);
        url.setExpiration(dto.getExpiration());
        return urlRepository.save(url);
    }

    @Override
    public Url deleteByShortKey(String shortKey) {
        return urlRepository.deleteByShortKey(shortKey);
    }
}