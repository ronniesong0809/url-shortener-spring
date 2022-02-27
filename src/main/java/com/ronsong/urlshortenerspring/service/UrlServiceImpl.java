package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.repository.UrlRepository;
import com.ronsong.urlshortenerspring.utils.Md5Utils;
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
    public Url shorten(ShortenDTO dto) {
        String shortKey = Md5Utils.get62Hex(Md5Utils.getHashCode(dto.getUrl()));

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
    public Url findByLongUrl(ShortenDTO dto) {
        return urlRepository.findByLongUrl(dto.getUrl());
    }

    @Override
    public Url update(ShortenDTO dto) {
        Url url = findByLongUrl(dto);
        url.setExpiration(dto.getExpiration());
        return urlRepository.save(url);
    }
}