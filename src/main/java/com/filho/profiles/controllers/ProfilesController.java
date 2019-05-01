package com.filho.profiles.controllers;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.data.ProfileRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profiles")
public class ProfilesController {

    @Autowired
    PagedResourcesAssembler<Profile> assembler;

    @Autowired
    ProfileRepositoryExt repository;

    @GetMapping
    public Resources<Resource<Profile>> findProfiles(
            @RequestParam(value = "photo", required = false) final Boolean hasPhoto,
            @RequestParam(value = "contact", required = false) final Boolean hasContact,
            @RequestParam(value = "favorite", required = false) final Boolean isFavorite,
            @RequestParam(value = "compatScore", required = false) final String compatibilityScoreRange,
            @RequestParam(value = "height", required = false) final String heightRange,
            @RequestParam(value = "distance", required = false) final String distance,
            final Pageable pagination) {

        ProfileFilter filter = toFilter(
                hasPhoto,
                hasContact,
                isFavorite,
                compatibilityScoreRange,
                heightRange,
                distance
        );

        final Page<Profile> searchResults = repository.findByCriteria(filter, pagination);
        return assembler.toResource(searchResults);
    }

    /**
     *
     * @param hasPhoto
     * @param hasContact
     * @param isFavorite
     * @param compatibilityScoreRange
     * @param heightRange
     * @param distance
     * @return
     */
    private ProfileFilter toFilter(
            Boolean hasPhoto,
            Boolean hasContact,
            Boolean isFavorite,
            String compatibilityScoreRange,
            String heightRange,
            String distance) {
        final ProfileFilter.ProfileFilterBuilder builder = ProfileFilter.builder()
                .hasPhoto(hasPhoto)
                .hasContact(hasContact)
                .isFavorite(isFavorite);

        return builder.build();
    }

}
