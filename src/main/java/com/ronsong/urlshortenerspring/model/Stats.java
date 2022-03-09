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
@Document(collection = "stats")
public class Stats {
    @Id
    private String id;
    private String shortKey;
    private Integer clicks;
    private String userIp;
    private String userAgent;
    private Date createdAt;
    private Date updatedAt;
}