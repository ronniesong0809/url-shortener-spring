package com.ronsong.urlshortenerspring.repository;

import com.ronsong.urlshortenerspring.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronsong
 */
@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
}