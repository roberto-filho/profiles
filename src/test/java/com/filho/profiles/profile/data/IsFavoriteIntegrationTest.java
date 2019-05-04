package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class IsFavoriteIntegrationTest extends AbstractProfileRepositoryIntegrationTest {

    @Test
    public void shouldFindProfileWithContactsWhenIsFavoriteIsTrue() {
        final ProfileFilter filter = ProfileFilter.builder()
                .isFavorite(true)
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getDisplayName().equals("I am a favorite"));
    }

    @Test
    public void shouldFindProfileNotFavoriteWhenIsFavoriteIsFalse() {
        final ProfileFilter filter = ProfileFilter.builder()
                .isFavorite(false)
                .build();

        final Page<Profile> queryResults = getProfileRepositoryExt().findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .allMatch(profile -> profile.getDisplayName().equals("I am not a favorite"));
    }

    private void createProfilesWithFavoriteTrueAndFalse(ProfileRepository repository) {
        final Profile favorite = Profile.builder()
                .displayName("I am a favorite")
                .favourite(true)
                .build();

        final Profile notAFavorite = Profile.builder()
                .displayName("I am not a favorite")
                .favourite(false)
                .build();

        final Profile iAmNotAFavorite = Profile.builder()
                .displayName("I am not a favorite")
                .favourite(false)
                .build();

        repository.saveAll(Arrays.asList(favorite, notAFavorite, iAmNotAFavorite));
    }

    @Override
    protected void createDataForTest(ProfileRepository repository) {
        createProfilesWithFavoriteTrueAndFalse(repository);
    }
}
