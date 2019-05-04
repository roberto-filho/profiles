package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CompatibilityScoreIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    @Test
    public void shouldFindProfileWithCompatilityInRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .compatibilityScoreRange(Pair.of(54, 70))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .anyMatch(profile -> profile.getCompatibilityScore().compareTo(BigDecimal.valueOf(0.70)) == 0)
                .anyMatch(profile -> profile.getCompatibilityScore().compareTo(BigDecimal.valueOf(0.54)) == 0);
    }

    @Test
    public void shouldFindProfileWhenOnlyOneInCompatibilityRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .compatibilityScoreRange(Pair.of(20, 50))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getCompatibilityScore().compareTo(BigDecimal.valueOf(0.31)) == 0);
    }

    @Test
    public void shouldFindNoProfilesWhenNoneInRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .compatibilityScoreRange(Pair.of(0, 20))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults).hasSize(0);
    }

    private void createProfilesWithVariousCompatibilityScores(ProfileRepository repository) {
        final Profile superCompati = Profile.builder()
                .displayName("I am compatible")
                .compatibilityScore(BigDecimal.valueOf(0.99))
                .build();

        final Profile iAmOk = Profile.builder()
                .displayName("I am ok")
                .compatibilityScore(BigDecimal.valueOf(0.70))
                .build();

        final Profile iAmNotSoCompatible = Profile.builder()
                .displayName("I am not so compatible")
                .compatibilityScore(BigDecimal.valueOf(0.54))
                .build();

        final Profile iAmLowScore = Profile.builder()
                .displayName("I am low score")
                .compatibilityScore(BigDecimal.valueOf(0.31))
                .build();

        repository.saveAll(Arrays.asList(superCompati, iAmNotSoCompatible, iAmOk, iAmLowScore));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithVariousCompatibilityScores(repository);
    }
}
