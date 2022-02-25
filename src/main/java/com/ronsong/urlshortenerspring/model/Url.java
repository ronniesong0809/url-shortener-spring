package com.ronsong.urlshortenerspring.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author ronsong
 */
@Data
@Document(collection = "url")
public class Url {
    int exception;
    Date createdAt;
    Date updatedAt;
    private String shortKey;
    private String shortUrl;
    private String longUrl;
}