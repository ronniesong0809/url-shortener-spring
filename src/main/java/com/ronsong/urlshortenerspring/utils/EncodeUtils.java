package com.ronsong.urlshortenerspring.utils;

import lombok.experimental.UtilityClass;

/**
 * utility class for encoding
 *
 * @author RonSong
 */
@UtilityClass
public class EncodeUtils {
    public static String encode(String longUrl) {
        return get62Hex(getHashCode(longUrl));
    }

    public static long getHashCode(String longUrl) {
        long hash = 0;
        for (int i = 0; i < longUrl.length(); i++) {
            char ch = longUrl.charAt(i);
            hash += (hash << 5) + ch;
        }
        return hash & 0x7FFFFFFF;
    }

    public static String get62Hex(long hash) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        hash = Math.abs(hash);
        StringBuilder sb = new StringBuilder();
        while (hash > 0) {
            sb.insert(0, str.charAt((int) (hash % 62)));
            hash /= 62;
        }
        return sb.toString();
    }
}