package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.service.UrlService;
import com.ronsong.urlshortenerspring.utils.EncodeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UrlControllerTests {
    @Value("${BASE_URL}")
    private String baseUrl;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlService urlService;

    private ShortenDTO dto;
    private Url url;

    @Before
    public void setUp() {
        dto = ShortenDTO.builder()
                .url("https://github.com/ronniesong0809")
                .expiration(0)
                .build();

        String shortKey = EncodeUtils.get62Hex(EncodeUtils.getHashCode("https://github.com/ronniesong0809"));

        url = Url.builder()
                .longUrl("https://github.com/ronniesong0809")
                .shortKey(shortKey)
                .shortUrl(baseUrl + '/' + shortKey)
                .createdAt(new Date())
                .updatedAt(new Date())
                .expiration(0)
                .build();
        List<Url> urls = List.of(url);

        given(urlService.shorten(dto)).willReturn(url);
        given(urlService.findAll()).willReturn(urls);
        given(urlService.findByShortKey("4mDmZ")).willReturn(url);
    }

    @Test
    public void testGetAllUrlsShouldReturnIsOk() throws Exception {
        mvc.perform(get("/all")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetByShortKeyShouldReturnIsMovedPermanently() throws Exception {
        mvc.perform(get("/4mDmZ")
                        .contentType("application/json"))
                .andExpect(status().isMovedPermanently());
    }

    @Test
    public void testPostShortenShouldReturnIsCreated() throws Exception {
        mvc.perform(post("/shorten")
                        .contentType("application/x-www-form-urlencoded")
                        .param("url", "https://github.com/ronniesong0809")
                        .param("expiration", "0"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPostShortenShouldReturnIsOk() throws Exception {
        testPostShortenShouldReturnIsCreated();
        given(urlService.exists(dto)).willReturn(true);
        given(urlService.update(dto)).willReturn(url);

        mvc.perform(post("/shorten")
                        .contentType("application/x-www-form-urlencoded")
                        .param("url", "https://github.com/ronniesong0809")
                        .param("expiration", "0"))
                .andExpect(status().isOk());
    }
}