package com.filho.profiles.service;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.data.ProfileRepository;
import com.filho.profiles.profile.data.ProfileRepositoryExt;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepositoryExt repositoryExt;
    private final ProfileRepository repository;

    /**
     * Searches for profiles using the given filters.
     * @param hasPhoto                if a profile has photo or not.
     * @param hasContact              if the profile has exchanged contacts or not.
     * @param isFavorite              filters profiles that are favorites or not.
     * @param compatibilityScoreRange range for filtering by compatibility score.
     * @param ageRange                range for filtering by age.
     * @param heightRange             range for filtering by height.
     * @param distance                filters by this distance from the logged in user.
     * @param jobTitle                the job title for filtering the results.
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
            String religion,
            String jobTitle,
            Pageable pagination) {

        ProfileFilter filter = ProfileFilter.fromRequestParams(
                hasPhoto,
                hasContact,
                isFavorite,
                compatibilityScoreRange,
                ageRange,
                heightRange,
                distance,
                religion,
                jobTitle
        );

        return repositoryExt.findByCriteria(filter, pagination);
    }

    /**
     * Gets the list of religions used from the records in the database.
     * @return a list with the religions.
     */
    public List<String> findDistinctReligions() {
        return repository.findDistinctReligions();
    }

    public List<String> findDistinctJobTitles() {
        return repository.findDistinctJobTitles();
    }
}
