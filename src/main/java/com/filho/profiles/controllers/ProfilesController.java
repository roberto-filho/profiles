package com.filho.profiles.controllers;

import com.filho.profiles.controllers.validation.ParameterValidator;
import com.filho.profiles.dto.ParameterValidationMessage;
import com.filho.profiles.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

    private ProfileService service;

    @Autowired
    public ProfilesController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity findProfiles(
            @RequestParam(value = "photo", required = false) final Boolean hasPhoto,
            @RequestParam(value = "contact", required = false) final Boolean hasContact,
            @RequestParam(value = "favorite", required = false) final Boolean isFavorite,
            @RequestParam(value = "compatScore", required = false) final String compatibilityScoreRange,
            @RequestParam(value = "age", required = false) final String ageRange,
            @RequestParam(value = "height", required = false) final String heightRange,
            @RequestParam(value = "distance", required = false) final String distance,
            final Pageable pagination) {

        ParameterValidator validator = new ParameterValidator();

        final List<ParameterValidationMessage> validationMessages =
                validator.validateParams(compatibilityScoreRange, ageRange, heightRange, distance);

        if (validationMessages.size() > 0) {
            return ResponseEntity.unprocessableEntity().body(validationMessages);
        }

        return ResponseEntity
                .ok(service.findProfiles(
                        hasPhoto,
                        hasContact,
                        isFavorite,
                        compatibilityScoreRange,
                        ageRange,
                        heightRange,
                        distance,
                        pagination));
    }

}
