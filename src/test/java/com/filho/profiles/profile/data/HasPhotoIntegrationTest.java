package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.google.common.base.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HasPhotoIntegrationTest {

    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 25);

    @Autowired
    ProfileRepository repository;

    @Autowired
    ProfileRepositoryExt repositoryExt;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    private void createProfileWithPhotoAndProfileWithoutPhoto() {
        final Profile withPhoto = Profile.builder()
                .displayName("I have a picture")
                .mainPhoto("https://picsum.photos/200")
                .build();
        final Profile noPhoto = Profile.builder()
                .displayName("I don't have a picture")
                .build();

        repository.saveAll(Arrays.asList(withPhoto, noPhoto));
    }

    @Test
    public void shouldFindProfileWithPhotosWhenHasPhotoIsTrue() {
        createProfileWithPhotoAndProfileWithoutPhoto();

        final ProfileFilter filter = ProfileFilter.builder().hasPhoto(true).build();

        Page<Profile> queryResults = repositoryExt.findByCriteria(filter, PageRequest.of(0, 25));

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getDisplayName().equals("I have a picture"));
    }

    @Test
    public void shouldFindProfilesWithNoPhotoWhenHasPhotoIsFalse() {
        createProfileWithPhotoAndProfileWithoutPhoto();

        final ProfileFilter filter = ProfileFilter.builder().hasPhoto(false).build();

        Page<Profile> queryResults = repositoryExt.findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(profile -> profile.getDisplayName().equals("I don't have a picture"));
    }

}