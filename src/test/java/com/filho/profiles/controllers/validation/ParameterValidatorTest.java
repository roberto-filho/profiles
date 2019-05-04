package com.filho.profiles.controllers.validation;

import com.filho.profiles.dto.ParameterValidationMessage;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterValidatorTest {

    private ParameterValidator validator = new ParameterValidator();

    @Test
    public void shouldGenerateMessageIfCompatibilityScoreRangeIsAbove99() {
        runCompatibilityScoreValidation("[101,120]");
    }

    @Test
    public void shouldGenerateMessageIfCompatibilityScoreRangeIsBelow1() {
        runCompatibilityScoreValidation("[0,99]");
    }

    @Test
    public void shouldGenerateMessageIfAgeRangeIsBelow18YearsOld() {
        runAgeValidation("[17,50]");
    }

    @Test
    public void shouldGenerateMessageIfAgeRangeIsAbove95yearsOld() {
        runAgeValidation("[18,96]");
    }

    @Test
    public void shouldGenerateMessageIfHeightRangeBelow135cm() {
        runHeightValidation("[125,200]");
    }

    @Test
    public void sholdGenerateMesageIfHeightRangeIsAbove210cm() {
        runHeightValidation("[135,300]");
    }

    private void runHeightValidation(String heightRange) {
        final List<ParameterValidationMessage> parameterValidationMessages = validator
                .validateParams(
                        null,
                        null,
                        heightRange,
                        null);

        assertThat(parameterValidationMessages)
                .hasSize(1)
                .allMatch(message -> message.getParameter().equals("height"));
    }

    private void runCompatibilityScoreValidation(String compatScoreRange) {
        final List<ParameterValidationMessage> messages = validator.validateParams(compatScoreRange, null, null, null);

        assertThat(messages)
                .hasSize(1)
                .allMatch(message -> message.getParameter().equals("compatScore"));
    }

    private void runAgeValidation(String ageRange) {
        final List<ParameterValidationMessage> parameterValidationMessages = validator
                .validateParams(
                        null,
                        ageRange,
                        null,
                        null);

        assertThat(parameterValidationMessages)
                .hasSize(1)
                .allMatch(message -> message.getParameter().equals("age"));
    }
}