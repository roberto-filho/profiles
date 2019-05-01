package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
            specs.add(ProfileSpecifications.hasPhoto(filter.getHasPhoto()));
        }

        return specs
                .stream()
                .reduce(emptySpec(), Specification::and);
    }

    private Specification<Profile> emptySpec() {
        return Specification.where(null);
    }

}
