package com.ronsong.urlshortenerspring.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author ronsong
 */
@Data
@Builder
public class UpdateDTO {
    @NotNull(message = "missing required parameter: expiration")
    @Range(min = 0, max = 30, message = "Must be in range (0, 30)")
    private Integer expiration;
}