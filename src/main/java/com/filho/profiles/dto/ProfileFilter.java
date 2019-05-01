package com.filho.profiles.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileFilter {
    private Boolean hasPhoto;
    private Boolean hasContact;
    private Boolean isFavorite;
}
