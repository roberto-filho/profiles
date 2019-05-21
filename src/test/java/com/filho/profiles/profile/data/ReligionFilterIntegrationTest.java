package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.Religion;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static com.filho.profiles.profile.Religion.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ReligionFilterIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    @Test
    public void shouldFindOnlyProfilesWithChristianReligionWhenChristianFilterUsed() {
        final ProfileFilter filter = ProfileFilter.builder()
                .religion(Christian.name())
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .allMatch(profile -> profile.getDisplayName().contains("Christian"));
    }

    @Test
    public void shouldFindNoProfilesWhenNonexistentReligionFilterUsed() {
        final ProfileFilter filter = ProfileFilter.builder()
                .religion("Scientology")
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(0);
    }

    private void createProfilesWithVariousReligions(ProfileRepository repository) {
        final Profile christianGuy = Profile.builder()
                .displayName("I am a Christian guy")
                .religion(Christian.name())
                .build();

        final Profile christianGirl = Profile.builder()
                .displayName("I am a Christian girl")
                .religion(Christian.name())
                .build();

        final Profile regularAtheist = Profile.builder()
                .displayName("I am a regular Atheist")
                .religion(Atheist.name())
                .build();

        final Profile agnostic = Profile.builder()
                .displayName("I am a Agnostic")
                .religion(Agnostic.name())
                .build();

        final Profile buddhist = Profile.builder()
                .displayName("I am very Buddhist")
                .religion(Buddhist.name())
                .build();

        final Profile jewish = Profile.builder()
                .displayName("I am a Jew")
                .religion(Jewish.name())
                .build();

        repository.saveAll(Arrays.asList(christianGuy, christianGirl, regularAtheist, agnostic, buddhist, jewish));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithVariousReligions(repository);
    }
}
