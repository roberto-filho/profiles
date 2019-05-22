package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static com.filho.profiles.profile.Religion.*;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTitleFilterIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    private static final String LAWYER = "Lawyer";

    @Test
    public void shouldFind3ProfilesWithLawyerJobTitleWhenLawyerFilterUsed() {
        final ProfileFilter filter = ProfileFilter.builder()
                .jobTitle(LAWYER)
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(3)
                .allMatch(profile -> profile.getDisplayName().contains(LAWYER));
    }

    @Test
    public void shouldFindNoProfilesWhenNonexistentJobTitleFilterUsed() {
        final ProfileFilter filter = ProfileFilter.builder()
                .religion("Psychologist")
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(0);
    }

    private void createProfilesWithVariousJobTitles(ProfileRepository repository) {
        final Profile doctor = Profile.builder()
                .displayName("I am a Doctor")
                .religion("Doctor")
                .build();

        final Profile ceo = Profile.builder()
                .displayName("I am a CEO")
                .religion("CEO")
                .build();

        final Profile accountant = Profile.builder()
                .displayName("I am a simple Accountant")
                .jobTitle("Accountant")
                .build();

        final Profile lawyer = Profile.builder()
                .displayName("I am a good Lawyer")
                .jobTitle(LAWYER)
                .build();

        final Profile lawyer2 = Profile.builder()
                .displayName("I am the best Lawyer")
                .jobTitle(LAWYER)
                .build();

        final Profile lawyer3 = Profile.builder()
                .displayName("I am an experienced Lawyer")
                .jobTitle(LAWYER)
                .build();

        repository.saveAll(Arrays.asList(doctor, ceo, accountant, lawyer, lawyer2, lawyer3));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithVariousJobTitles(repository);
    }
}
