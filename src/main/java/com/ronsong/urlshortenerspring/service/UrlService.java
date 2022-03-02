package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.UpdateDTO;
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

    /**
     * find by longUrl
     *
     * @param longUrl long url
     * @return url
     */
    Url findByLongUrl(String longUrl);

    /**
     * save url
     *
     * @param dto Shorten data transfer object
     * @return url
     */
    Url shorten(ShortenDTO dto);


    /**
     * find if exists by long url
     *
     * @param dto Shorten data transfer object
     * @return true if exists
     */
    Boolean exists(ShortenDTO dto);

    /**
     * update url
     *
     * @param dto Shorten data transfer object
     * @return url
     */
    Url updateByLongUrl(ShortenDTO dto);

    /**
     * update url
     *
     * @param shortKey short url
     * @param dto      horten data transfer object
     * @return url
     */
    Url updateByShortKey(String shortKey, UpdateDTO dto);


    /**
     * delete url by short key
     *
     * @param shortKey short url
     * @return url
     */
    Url deleteByShortKey(String shortKey);
}