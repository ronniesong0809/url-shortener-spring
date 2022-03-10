package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.model.Stats;
import com.ronsong.urlshortenerspring.repository.StatsRepository;
import com.ronsong.urlshortenerspring.service.StatsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatsServiceTests {
    private final MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @Autowired
    StatsService statsService;

    @MockBean

    private StatsRepository statsRepository;
    private Stats stats;
    private List<Stats> statsList;

    @Before
    public void setUp() {
        stats = Stats.builder()
                .shortKey("4mDmZ")
                .clicks(1)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .userIp("1.1.1.1")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        statsList = List.of(stats);

        when(statsRepository.findByShortKey("4mDmZ"))
                .thenReturn(stats);
        when(statsRepository.findAll())
                .thenReturn(statsList);
        when(statsRepository.save(stats))
                .thenReturn(stats);
    }

    @Test
    public void test_findByShortKey_key_should_return_stats() {
        Stats found = statsService.findByShortKey("4mDmZ");

        assertNotNull(found);
        assertEquals(stats, found);
    }

    @Test
    public void test_findAll_should_return_a_list_of_stats() {
        Collection<Stats> foundUrls = statsService.findAll();

        assertNotNull(foundUrls);
        assertEquals(statsList, foundUrls);
    }

    @Test
    public void test_save_should_return_stats() {
        Stats found = statsService.save("4mDmZ", mockRequest);

        assertNotNull(found);
        assertEquals(stats, found);
    }
}