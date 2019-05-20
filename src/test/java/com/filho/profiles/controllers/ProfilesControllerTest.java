package com.filho.profiles.controllers;

import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.Religion;
import com.filho.profiles.profile.data.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.filho.profiles.profile.Religion.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfilesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProfileRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void shouldFilterProfilesWithNoMainPhotoWhenUsingPhotoQueryParam() throws Exception {
        final Profile withPhoto = Profile.builder()
                .mainPhoto("http://thecatapi.com/api/images/get?format=src&type=gif")
                .displayName("BobTheBuilder")
                .build();

        final Profile withNoPhoto = Profile.builder()
                .mainPhoto(null)
                .displayName("BobTheBuilderWithoutPicture")
                .build();

        repository.save(withPhoto);
        repository.save(withNoPhoto);

        // For testing we do not use the context path suffix, because a "mock server" is used
        mvc.perform(get("/profiles?page=0&photo=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].displayName", is("BobTheBuilderWithoutPicture")))
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    public void shouldFilterProfilesByReligionWhenUsingReligionQueryParam() throws Exception {
        Profile muslim = Profile.builder()
                .displayName("Muslim guy")
                .religion(Islam.name())
                .build();

        Profile christianGirl = Profile.builder()
                .displayName("Christian girl")
                .religion(Christian.name())
                .build();

        repository.saveAll(Arrays.asList(christianGirl, muslim));

        mvc.perform(get("/profiles?religion=Christian"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].displayName", is("Christian girl")))
                .andExpect(jsonPath("$.content", hasSize(1)));
    }
}
