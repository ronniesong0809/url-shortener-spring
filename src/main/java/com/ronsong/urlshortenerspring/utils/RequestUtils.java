package com.ronsong.urlshortenerspring.utils;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ronsong
 */
@UtilityClass
public class RequestUtils {
    public static String getIpAddress(HttpServletRequest request) {
        if (request.getRemoteAddr() != null) {
            return request.getRemoteAddr();
        }

        return request.getHeader("X-FORWARDED-FOR") != null
                ? request.getHeader("X-FORWARDED-FOR")
                : null;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : null;
    }
}