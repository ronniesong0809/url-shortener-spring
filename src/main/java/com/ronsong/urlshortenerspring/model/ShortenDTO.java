package com.ronsong.urlshortenerspring.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

/**
 * @author ronsong
 */
@Data
@Builder
public class ShortenDTO {
    @URL
    private String url;

    @Range(min = 0, max = 30, message = "Must be in range (0, 30)")
    private Integer expiration;
}