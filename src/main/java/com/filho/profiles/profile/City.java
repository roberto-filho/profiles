package com.filho.profiles.profile;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class City {
    private String name;
    @Column(precision = 12, scale = 8)
    private BigDecimal lat;
    @Column(precision = 12, scale = 8)
    private BigDecimal lon;
}

