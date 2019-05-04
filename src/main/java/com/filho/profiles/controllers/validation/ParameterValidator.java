package com.filho.profiles.controllers.validation;

import com.filho.profiles.dto.ParameterValidationMessage;
import com.filho.util.RangeUtil;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ParameterValidator {

    private List<ParameterValidationMessage> validationMessages = new ArrayList<>();

    public List<ParameterValidationMessage> validateParams(String compatibilityScoreRange, String ageRange, String heightRange, String distance) {


        if (compatibilityScoreRange != null) {
            rangeValidation(
                    compatibilityScoreRange,
                    1,
                    99,
                    "compatScore",
                    "Invalid compatibility score range. Valid range is from 1 to 99");
        }

        if (ageRange != null) {
            rangeValidation(
                    ageRange,
                    18,
                    95,
                    "age",
                    "Invalid age range. Valid range is from 18 to 95");
        }

        if (heightRange != null) {
            rangeValidation(
                    heightRange,
                    135,
                    210,
                    "height",
                    "Invalid height range. Valida range is from 135cm to 210 cm."
            );
        }

        return validationMessages;
    }

    private void rangeValidation(String range, int minimum, int maximum, String field, String validationMessage) {
        final Pair<Integer, Integer> age = RangeUtil.parseRange(range);

        if (age.getFirst() < minimum || age.getSecond() > maximum) {
            final ParameterValidationMessage message = new ParameterValidationMessage(
                    field,
                    validationMessage
            );

            validationMessages.add(message);
        }
    }

}
