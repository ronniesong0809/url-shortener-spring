package com.ronsong.urlshortenerspring.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author ronsong
 */
@Data
@Builder
@Document(collection = "url")
public class Url {
    @Indexed(unique = true)
    private String shortKey;
    private String shortUrl;
    private String longUrl;
    private int exception;
    private Date createdAt;
    private Date updatedAt;
}