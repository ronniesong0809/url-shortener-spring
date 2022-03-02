package com.ronsong.urlshortenerspring.repository;

import com.ronsong.urlshortenerspring.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronsong
 */
@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    /**
     * find by shortKey
     *
     * @param shortKey the short key
     * @return the url
     */
    Url findByShortKey(String shortKey);

    /**
     * find by longUrl
     *
     * @param longUrl the long url
     * @return the url
     */
    Url findByLongUrl(String longUrl);

    /**
     * find if exists by long url
     *
     * @param longUrl the long url
     * @return true if exists
     */
    Boolean existsByLongUrl(String longUrl);


    /**
     * delete by short key
     *
     * @param shortKey the short key
     * @return the url
     */
    Url deleteByShortKey(String shortKey);
}