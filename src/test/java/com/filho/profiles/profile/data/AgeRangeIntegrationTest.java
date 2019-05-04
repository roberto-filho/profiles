package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AgeRangeIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    @Test
    public void shouldFindProfileWithAgeInRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .ageRange(Pair.of(18, 37))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .anyMatch(profile -> profile.getAge().intValue() == 35)
                .anyMatch(profile -> profile.getAge().intValue() == 22);
    }

    @Test
    public void shouldFindNoProfilesWhenNoRecordsInRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .ageRange(Pair.of(18, 20))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(0);
    }

    @Test
    public void shouldFindProfilesWhenMinimumAndMaximumAreTheSame() {
        final ProfileFilter filter = ProfileFilter.builder()
                .ageRange(Pair.of(90, 90))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getAge().intValue() == 90);
    }

    private void createProfilesWithVariousAges(ProfileRepository repository) {
        final Profile oldest = Profile.builder()
                .displayName("I am old")
                .age(90)
                .build();

        final Profile iAmYounger = Profile.builder()
                .displayName("I am younger")
                .age(54)
                .build();

        final Profile iAmAMother = Profile.builder()
                .displayName("I am a mother")
                .age(35)
                .build();

        final Profile iAmYoungAndReckless = Profile.builder()
                .displayName("I am young and reckless")
                .age(22)
                .build();

        repository.saveAll(Arrays.asList(oldest, iAmYoungAndReckless, iAmYounger, iAmAMother));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithVariousAges(repository);
    }
}
