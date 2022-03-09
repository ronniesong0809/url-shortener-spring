package com.ronsong.urlshortenerspring.repository;

import com.ronsong.urlshortenerspring.model.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronsong
 */
@Repository
public interface StatsRepository extends MongoRepository<Stats, String> {
    /**
     * find stats by url
     *
     * @param shortKey the short key
     * @return the stats
     */
    Stats findByShortKey(String shortKey);
}