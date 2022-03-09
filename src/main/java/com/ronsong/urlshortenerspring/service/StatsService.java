package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.Stats;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author ronsong
 */
public interface StatsService {
    /**
     * find all Stats
     *
     * @return all Urls in the database
     */
    Collection<Stats> findAll();

    /**
     * find Stats by short key
     *
     * @param shortKey the shortKey of the Stats to find
     * @return the Stats for the given shortKey
     */
    Stats findByShortKey(String shortKey);

    /**
     * save a Stats
     *
     * @param shortKey the shortKey of the Stats to find
     * @return the Stats for the given shortKey
     */
    Stats save(String shortKey, HttpServletRequest request);
}