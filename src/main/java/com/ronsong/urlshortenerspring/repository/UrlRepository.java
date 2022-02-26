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
     * @param LongUrl the long url
     * @return the url
     */
    Url findByLongUrl(String LongUrl);
}