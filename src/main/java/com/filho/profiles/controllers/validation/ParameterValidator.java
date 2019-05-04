package com.filho.profiles.controllers.validation;

import com.filho.profiles.dto.ParameterValidationMessage;
import com.filho.util.RangeUtil;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates the parameters sent through the API that need validation: "compatScore", "age", "height", "distance".
 */
public class ParameterValidator {

    private List<ParameterValidationMessage> validationMessages = new ArrayList<>();

    public List<ParameterValidationMessage> validateParams(String compatibilityScoreRange, String ageRange, String heightRange, String distance) {
        if (compatibilityScoreRange != null) {
            validateRange(
                    compatibilityScoreRange,
                    1,
                    99,
                    "compatScore",
                    "Invalid compatibility score range. Valid range is from 1 to 99");
        }

        if (ageRange != null) {
            validateRange(
                    ageRange,
                    18,
                    95,
                    "age",
                    "Invalid age range. Valid range is from 18 to 95");
        }

        if (heightRange != null) {
            validateRange(
                    heightRange,
                    135,
                    210,
                    "height",
                    "Invalid height range. Valid range is from 135cm to 210 cm."
            );
        }

        if (distance != null ) {
            Integer parsedDistance = parseInt(distance);
            if (parsedDistance == null || parsedDistance.intValue() < 30 || parsedDistance.intValue() > 300) {
                final ParameterValidationMessage message = new ParameterValidationMessage(
                        "distance",
                        "Invalid distance. Valid distance is between 30 and 300."
                );

                validationMessages.add(message);
            }
        }

        return validationMessages;
    }

    private Integer parseInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private void validateRange(String range, int minimum, int maximum, String field, String validationMessage) {
        final Pair<Integer, Integer> age = RangeUtil.parseRange(range);

        if (age.getFirst().intValue() < minimum || age.getSecond().intValue() > maximum) {
            final ParameterValidationMessage message = new ParameterValidationMessage(
                    field,
                    validationMessage
            );

            validationMessages.add(message);
        }
    }

}
