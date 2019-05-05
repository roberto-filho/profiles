package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HeightRangeIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    @Test
    public void shouldNotFindProfilesWhenAllRecordsAreOutsideHeightRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .heightRange(Pair.of(136, 149))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(0);
    }

    @Test
    public void shouldFindProfilesWhenMinAndMaxAreTheSame() {
        final ProfileFilter filter = ProfileFilter.builder()
                .heightRange(Pair.of(151, 151))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getHeightInCm().intValue() == 151);
    }

    @Test
    public void shouldFindProfilesWhenThereAreRecordsInRange() {
        final ProfileFilter filter = ProfileFilter.builder()
                .heightRange(Pair.of(150, 165))
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .anyMatch(profile -> profile.getHeightInCm().intValue() == 151)
                .anyMatch(profile -> profile.getHeightInCm().intValue() == 163);
    }

    private void createProfilesWithVariousHeights(ProfileRepository repository) {
        new ArrayList<Profile>() {{

        }};
        final Profile iAmShort = Profile.builder()
                .displayName("I am short")
                .heightInCm(151L)
                .build();

        final Profile iAmNotTheShortest = Profile.builder()
                .displayName("I am not the shortest")
                .heightInCm(163L)
                .build();

        final Profile iAmAverage = Profile.builder()
                .displayName("I am average")
                .heightInCm(172L)
                .build();

        final Profile iAmTall = Profile.builder()
                .displayName("I am tall")
                .heightInCm(185L)
                .build();

        final Profile iAmTallest = Profile.builder()
                .displayName("I am tallest")
                .heightInCm(203L)
                .build();

        repository.saveAll(Arrays.asList(iAmShort, iAmTall, iAmNotTheShortest, iAmAverage, iAmTallest));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithVariousHeights(repository);
    }
}
