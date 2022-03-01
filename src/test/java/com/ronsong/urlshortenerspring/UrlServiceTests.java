package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.model.ShortenDTO;
import com.ronsong.urlshortenerspring.model.Url;
import com.ronsong.urlshortenerspring.repository.UrlRepository;
import com.ronsong.urlshortenerspring.service.UrlService;
import com.ronsong.urlshortenerspring.utils.EncodeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlServiceTests {

    @Value("${BASE_URL}")
    private String baseUrl;

    @Autowired
    private UrlService urlService;

    @MockBean
    private UrlRepository urlRepository;

    private List<Url> urls;
    private Url url;
    private ShortenDTO dto;

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
        urls = List.of(url);

        when(urlRepository.findAll())
                .thenReturn(urls);
        when(urlRepository.findByShortKey("4mDmZ"))
                .thenReturn(url);
        when(urlRepository.findByLongUrl("https://github.com/ronniesong0809"))
                .thenReturn(url);
        when(urlRepository.save(url))
                .thenReturn(url);
        when(urlRepository.existsByLongUrl("https://github.com/ronniesong0809"))
                .thenReturn(true);
    }

    @Test
    public void testFindAllShouldReturnAListOfUrl() {
        Collection<Url> foundUrls = urlService.findAll();

        assertNotNull(foundUrls);
        assertEquals(urls, foundUrls);
        assertEquals(1, foundUrls.size());
    }

    @Test
    public void testFindByShortKeyShouldReturnUrl() {
        Url found = urlService.findByShortKey("4mDmZ");

        assertNotNull(found);
        assertEquals(url, found);
    }


    @Test
    public void testFindByLongUrlShouldReturnUrl() {
        Url found = urlService.findByLongUrl(dto);

        assertNotNull(found);
        assertEquals(url, found);
    }

    @Test
    public void testShortenShouldReturnUrl() {
        Url shortened = urlService.shorten(dto);

        assertNotNull(shortened);
        assertEquals(url.getShortKey(), shortened.getShortKey());
    }

    @Test
    public void testExistsShouldReturnTrue() {
        Boolean existed = urlService.exists(dto);

        assertTrue(existed);
    }


    @Test
    public void testUpdateShouldReturnUpdatedUrl() {
        testFindByLongUrlShouldReturnUrl();
        dto.setExpiration(7);
        Url updated = urlService.update(dto);

        assertNotNull(updated);
        assertEquals(url, updated);
    }
}