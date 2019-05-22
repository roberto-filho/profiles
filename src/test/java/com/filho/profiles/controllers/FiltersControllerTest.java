package com.filho.profiles.controllers;

import com.filho.profiles.profile.Profile;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FiltersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProfileRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturn2ReligionsWhen2ReligionsAreInTheDatabase() throws Exception {
        Profile atheist = Profile.builder().religion("Atheist").displayName("Atheist guy").build();
        Profile muslim = Profile.builder().religion("Islam").displayName("Muslim girl").build();
        Profile muslim2 = Profile.builder().religion("Islam").displayName("Muslim guy").build();

        repository.saveAll(Arrays.asList(atheist, muslim, muslim2));

        mvc.perform(get("/filters/religion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturn3JobTitlesWhen3UniqueJobTitlesInDatabase() throws Exception {
        Profile accountant = Profile.builder().jobTitle("Accountant").displayName("The accountant guy").build();
        Profile accountant2 = Profile.builder().jobTitle("Accountant").displayName("The old accountant guy").build();
        Profile lawyer = Profile.builder().jobTitle("Corporate Lawyer").displayName("The suit and tie guy").build();
        Profile lawyer2 = Profile.builder().jobTitle("Lawyer").displayName("Regular Lawyer").build();
        Profile psychologist = Profile.builder().jobTitle("Psychologist").displayName("The suit and tie guy").build();

        repository.saveAll(Arrays.asList(accountant, accountant2, lawyer, lawyer2, psychologist));

        mvc.perform(get("/filters/job-title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}