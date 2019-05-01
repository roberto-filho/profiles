package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import com.google.common.base.Strings;
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
public class ProfileRepositoryExtIntegrationTest {

    @Autowired
    ProfileRepository repository;

    @Autowired
    ProfileRepositoryExt repositoryExt;

    @Test
    public void shouldFindProfileWithPhotosWhenHasPhotoIsTrue() {
        final Profile withPhoto = Profile.builder()
                .mainPhoto("https://picsum.photos/200")
                .build();
        final Profile noPhoto = Profile.builder().build();

        repository.saveAll(Arrays.asList(withPhoto, noPhoto));

        final ProfileFilter filter = ProfileFilter.builder().hasPhoto(true).build();

        Page<Profile> queryResults = repositoryExt.findByCriteria(filter, PageRequest.of(0, 25));

        assertThat(queryResults).allMatch(profile -> !Strings.isNullOrEmpty(profile.getMainPhoto()));
    }
}