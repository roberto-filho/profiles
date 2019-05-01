package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

public class ProfileSpecifications {

    public static Specification<Profile> hasPhoto(boolean hasPhoto) {
        return (Specification<Profile>) (root, query, criteriaBuilder) ->
                hasPhoto
                ? root.get("mainPhoto").isNotNull()
                : root.get("mainPhoto").isNull();
    }
}
