package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.Url;

import java.util.Collection;

/**
 * @author ronsong
 */
public interface UrlService {
    /**
     * find all urls
     *
     * @return all urls
     */
    Collection<Url> findAll();

    /**
     * find by shortKey
     *
     * @param shortKey short key
     * @return url
     */
    Url findByShortKey(String shortKey);
}