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

    /**
     * Searches for profiles using the given filters.
     * @param hasPhoto                if a profile has photo or not.
     * @param hasContact              if the profile has exchanged contacts or not.
     * @param isFavorite              filters profiles that are favorites or not.
     * @param compatibilityScoreRange range for filtering by compatibility score.
     * @param ageRange                range for filtering by age.
     * @param heightRange             range for filtering by height.
     * @param distance                filters by this distance from the logged in user.
     * @param pagination              pagination information.
     * @return
     */
    public Page<Profile> findProfiles(
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
