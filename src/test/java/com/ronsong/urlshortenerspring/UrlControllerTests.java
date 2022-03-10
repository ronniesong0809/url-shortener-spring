package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.UpdateDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.service.StatsService;
import com.ronsong.urlshortenerspring.service.UrlService;
import com.ronsong.urlshortenerspring.utils.EncodeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UrlControllerTests {
    private final MockHttpServletRequest mockRequest = new MockHttpServletRequest();

    @Value("${BASE_URL}")
    private String baseUrl;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UrlService urlService;

    @MockBean
    private StatsService statsService;

    private ShortenDTO shortenDto;

    @Before
    public void setUp() {

        shortenDto = ShortenDTO.builder()
                .url("https://github.com/ronniesong0809")
                .expiration(0)
                .build();

        UpdateDTO updateDto = UpdateDTO.builder()
                .expiration(1)
                .build();

        String shortKey = EncodeUtils.get62Hex(EncodeUtils.getHashCode("https://github.com/ronniesong0809"));

        Url url = Url.builder()
                .longUrl("https://github.com/ronniesong0809")
                .shortKey(shortKey)
                .shortUrl(baseUrl + '/' + shortKey)
                .createdAt(new Date())
                .updatedAt(new Date())
                .expiration(0)
                .build();
        List<Url> urls = List.of(url);

        given(urlService.shorten(shortenDto))
                .willReturn(url);
        given(urlService.findAll())
                .willReturn(urls);
        given(urlService.findByShortKey("4mDmZ"))
                .willReturn(url);
        given(urlService.updateByLongUrl(shortenDto))
                .willReturn(url);
        given(urlService.updateByShortKey("4mDmZ", updateDto))
                .willReturn(url);
        given(urlService.deleteByShortKey("4mDmZ"))
                .willReturn(url);
        given(urlService.redirectToLongUrl("4mDmZ", mockRequest))
                .willReturn(url);
        Mockito.when(urlService.redirectToLongUrl(eq("4mDmZ"), any()))
                .thenReturn(url);
    }

    @Test
    public void test_get_all_urls_should_return_is_ok() throws Exception {
        mvc.perform(get("/all")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_get_by_short_key_should_return_is_moved_permanently() throws Exception {
        mvc.perform(get("/4mDmZ")
                        .requestAttr("request", mockRequest)
                        .contentType("application/json"))
                .andExpect(status().isFound());
    }

    @Test
    public void test_post_shorten_should_return_is_created() throws Exception {
        mvc.perform(post("/shorten")
                        .contentType("application/x-www-form-urlencoded")
                        .param("url", "https://github.com/ronniesong0809")
                        .param("expiration", "0"))
                .andExpect(status().isCreated());
    }

    @Test
    public void test_post_shorten_should_return_is_ok() throws Exception {
        given(urlService.exists(shortenDto))
                .willReturn(true);

        mvc.perform(post("/shorten")
                        .contentType("application/x-www-form-urlencoded")
                        .param("url", "https://github.com/ronniesong0809")
                        .param("expiration", "0"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_put_shorten_should_return_is_ok() throws Exception {
        mvc.perform(put("/4mDmZ")
                        .contentType("application/x-www-form-urlencoded")
                        .param("expiration", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_delete_url_by_short_key_should_return_is_ok() throws Exception {
        mvc.perform(delete("/4mDmZ")
                        .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk());
    }
}