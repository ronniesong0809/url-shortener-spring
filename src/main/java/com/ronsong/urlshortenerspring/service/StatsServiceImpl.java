package com.ronsong.urlshortenerspring.service;

import com.ronsong.urlshortenerspring.model.Stats;
import com.ronsong.urlshortenerspring.repository.StatsRepository;
import com.ronsong.urlshortenerspring.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;

/**
 * @author ronsong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatsServiceImpl implements StatsService {
    @Autowired
    StatsRepository statsRepository;

    @Override
    public Collection<Stats> findAll() {
        return statsRepository.findAll();
    }

    @Override
    public Stats findByShortKey(String shortKey) {
        return statsRepository.findByShortKey(shortKey);
    }

    @Override
    public Stats save(String shortKey, HttpServletRequest request) {
        Stats stats = findByShortKey(shortKey);
        String ip = RequestUtils.getIpAddress(request);
        String userAgent = RequestUtils.getUserAgent(request);

        if (stats == null) {
            stats = Stats.builder()
                    .shortKey(shortKey)
                    .clicks(1)
                    .userIp(ip)
                    .userAgent(userAgent)
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
        } else {
            stats.setClicks(stats.getClicks() + 1);
            stats.setUpdatedAt(new Date());
            stats.setUserIp(ip);
            stats.setUserAgent(userAgent);
        }
        return statsRepository.save(stats);
    }
}