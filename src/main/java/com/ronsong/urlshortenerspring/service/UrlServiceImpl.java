package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author ronsong
 */
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Override
    public Collection<Url> findAll() {
        return urlRepository.findAll();
    }
}