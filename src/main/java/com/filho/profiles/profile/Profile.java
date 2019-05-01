package com.filho.profiles.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id @GeneratedValue @Builder.Default
    private UUID id = UUID.randomUUID();
    private String displayName;
    private String jobTitle;
    private Integer age;
    private Long heightInCm;
    private String mainPhoto;
    @Column(precision = 3, scale = 2)
    private BigDecimal compatibilityScore;
    private Integer contactsExchanged;
    private boolean favourite;
    @Enumerated(EnumType.STRING)
    private Religion religion;

    @Embedded
    private City city;
}
