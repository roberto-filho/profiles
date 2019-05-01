package com.filho.profiles.controllers;

import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.data.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

        mvc.perform(get("/api/profiles?page=0&photo=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.profiles[0].displayName", is("BobTheBuilderWithoutPicture")))
        ;
    }
}