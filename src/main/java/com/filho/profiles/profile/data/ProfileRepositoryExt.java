package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.City;
import com.filho.profiles.profile.Profile;
import com.filho.util.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.filho.profiles.profile.data.ProfileSpecifications.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Repository
public class ProfileRepositoryExt {

    @Autowired
    ProfileRepository repository;

    @SuppressWarnings("unchecked")
    public Page<Profile> findByCriteria(ProfileFilter filter, Pageable pagination) {
        return repository.findAll(toSpec(filter), pagination);
    }

    /**
     * Reads the data from the filter object and creates a specification based on it.
     * @param filter the object with the filtering information.
     * @return the specification with the combination of the rules described in the
     * filter object.
     */
    private Specification<Profile> toSpec(ProfileFilter filter) {
        List<Specification<Profile>> specs = new ArrayList<>();

        if (filter.getHasPhoto() != null) {
            specs.add(hasPhoto(filter.getHasPhoto()));
        }

        if (filter.getHasContact() != null) {
            specs.add(hasContacts(filter.getHasContact()));
        }

        if (filter.getIsFavorite() != null) {
            specs.add(isFavorite(filter.getIsFavorite()));
        }

        final Pair<Integer, Integer> compatRange = filter.getCompatibilityScoreRange();
        if (compatRange != null) {
            specs.add(isCompatilityInRange(compatRange.getFirst(), compatRange.getSecond()));
        }

        final Pair<Integer, Integer> ageRange = filter.getAgeRange();
        if (ageRange != null) {
            specs.add(isAgeInRange(ageRange.getFirst(), ageRange.getSecond()));
        }

        final Pair<Integer, Integer> heightRange = filter.getHeightRange();
        if (heightRange != null) {
            specs.add(isHeightInRange(heightRange.getFirst(), heightRange.getSecond()));
        }

        if (filter.getDistanceRadiusInKm() != null) {
            final City city = LoggedInUser.getInstance().getCity();

            specs.add(isInKmRange(
                    filter.getDistanceRadiusInKm(),
                    city.getLat(),
                    city.getLon()
            ));
        }

        if (isNotBlank(filter.getReligion())) {
            specs.add(filterByReligion(filter.getReligion()));
        }

        if (isNotBlank(filter.getJobTitle())) {
            specs.add(filterByJobTitle(filter.getJobTitle()));
        }

        return specs
                .stream()
                .reduce(emptySpecification(), Specification::and);
    }

    private Specification<Profile> emptySpecification() {
        return Specification.where(null);
    }

}
