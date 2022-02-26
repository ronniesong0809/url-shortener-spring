package com.ronsong.urlshortenerspring.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author RonSong
 */
@Slf4j
public class Md5Utils {
    private static final String STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static long getHashCode(String longUrl) {
        long hash = 0;
        for (int i = 0; i < longUrl.length(); i++) {
            char ch = longUrl.charAt(i);
            hash += (hash << 5) + ch;
        }
        return hash & 0x7FFFFFFF;
    }

    public static String get62Hex(long hash) {
        hash = Math.abs(hash);
        StringBuilder sb = new StringBuilder();
        while (hash > 0) {
            sb.insert(0, STR.charAt((int) (hash % 62)));
            hash /= 62;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String url = "https://github.com/ronniesong0809";
        long hash = Md5Utils.getHashCode(url);
        String hex = Md5Utils.get62Hex(hash);
        log.info("url: [{}] -> hash: [{}]", url, hash);
        log.info("hash: [{}] -> hex: [{}]", hash, hex);
    }
}