package com.ronsong.urlshortenerspring.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author ronsong
 */
@Data
@Builder
@Document(collection = "url")
public class Url {
    @Id
    private String id;
    private String shortKey;
    private String shortUrl;
    private String longUrl;
    private Integer expiration;
    private Date createdAt;
    private Date updatedAt;
}