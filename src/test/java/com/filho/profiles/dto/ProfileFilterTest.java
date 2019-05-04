package com.filho.profiles.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.data.util.Pair;

public class ProfileFilterTest {

    @Test
    public void shouldParseCompatibilityRangeWhenInformed() {
        String compatRange = "[1,5]";

        final ProfileFilter profileFilter = ProfileFilter.fromRequestParams(
                null,
                null,
                null,
                compatRange,
                null,
                null,
                null);

        Assertions.assertThat(profileFilter)
                .hasFieldOrPropertyWithValue("compatibilityScoreRange", Pair.of(1,5));
    }

    @Test
    public void shouldNotThrowErrosWhenInputInWrongFormat() {
        String compatRange = "asdf";

        final ProfileFilter profileFilter = ProfileFilter.fromRequestParams(
                null,
                null,
                null,
                compatRange,
                null,
                null,
                null);

        Assertions.assertThat(profileFilter)
                .hasFieldOrPropertyWithValue("compatibilityScoreRange", null);
    }

    @Test
    public void shouldOrderCompatibilityRangeValuesByHighestToLowest() {
        String compatRange = "[70,30]";

        final ProfileFilter profileFilter = ProfileFilter.fromRequestParams(
                null,
                null,
                null,
                compatRange,
                null,
                null,
                null);

        Assertions.assertThat(profileFilter)
                .hasFieldOrPropertyWithValue("compatibilityScoreRange", Pair.of(30, 70));
    }
}