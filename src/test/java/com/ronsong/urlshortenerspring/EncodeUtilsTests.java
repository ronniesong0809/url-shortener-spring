package com.ronsong.urlshortenerspring;

import com.ronsong.urlshortenerspring.utils.EncodeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncodeUtilsTests {

    @Test
    public void test_encode_should_return_a_hex() {
        String hex = EncodeUtils.encode("https://github.com/ronniesong0809");

        assertEquals("4mDmZ", hex);
    }

    @Test
    public void test_getHash_code_should_return_a_hash() {
        long hash = EncodeUtils.getHashCode("https://github.com/ronniesong0809");

        assertEquals(64499901, hash);
    }

    @Test
    public void test_get62Hex_should_return_a_hex() {
        String hex = EncodeUtils.get62Hex(64499901);

        assertEquals("4mDmZ", hex);
    }
}