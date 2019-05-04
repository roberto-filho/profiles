package com.filho.profiles.service;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.data.ProfileRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileRepositoryExt repositoryExt;

    @Autowired
    public ProfileService(ProfileRepositoryExt repositoryExt) {
        this.repositoryExt = repositoryExt;
    }

    public Page<Profile> findByCriteria(
            Boolean hasPhoto,
            Boolean hasContact,
            Boolean isFavorite,
            String compatibilityScoreRange,
            String ageRange,
            String heightRange,
            String distance,
            Pageable pagination) {

        ProfileFilter filter = ProfileFilter.fromRequestParams(
                hasPhoto,
                hasContact,
                isFavorite,
                compatibilityScoreRange,
                ageRange,
                heightRange,
                distance
        );

        return repositoryExt.findByCriteria(filter, pagination);
    }
}
