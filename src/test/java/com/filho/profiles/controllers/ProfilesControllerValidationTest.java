package com.filho.profiles.controllers;

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
public class ProfilesControllerValidationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldRespondWithStatus200WhenHeightQueryParamIs150and170() throws Exception {
        mvc.perform(get(String.format("/api/profiles?height=[150,170]")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRespondWith422WhenHeightQueryParamIs134and200() throws Exception {
        makeRequestAndCheckStatus("height", "[134,200]");
    }

    @Test
    public void shouldRespondWith422WhenDistanceQueryParamIs29() throws Exception {
        makeRequestAndCheckStatus("distance", "29");
    }

    @Test
    public void shouldRespondWith422WhenAgeQueryParamIs25and100() throws Exception {
        makeRequestAndCheckStatus("age", "[25,100]");
    }

    private void makeRequestAndCheckStatus(final String paramName, final String paramValue) throws Exception {
        mvc.perform(get(String.format("/api/profiles?%s=%s", paramName, paramValue)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.[0].parameter", is(paramName)));
    }
}
