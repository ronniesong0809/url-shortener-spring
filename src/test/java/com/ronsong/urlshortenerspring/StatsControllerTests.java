package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.model.Stats;
import com.ronsong.urlshortenerspring.service.StatsService;
import com.ronsong.urlshortenerspring.service.UrlService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StatsControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatsService statsService;

    @MockBean
    private UrlService urlService;

    @Before
    public void setup() {
        Stats stats = Stats.builder()
                .shortKey("4mDmZ")
                .clicks(1)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .userIp("1.1.1.1")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        List<Stats> statsList = List.of(stats);

        given(statsService.findAll())
                .willReturn(statsList);
        given(statsService.findByShortKey("4mDmZ"))
                .willReturn(stats);
    }

    @Test
    public void test_get_by_short_key_should_return_is_ok() throws Exception {
        mvc.perform(get("/all/stats")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_all_stats_should_return_is_ok() throws Exception {
        mvc.perform(get("/4mDmZ/stats")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}