package com.filho.util;

import com.filho.profiles.profile.City;
import com.filho.profiles.profile.Profile;

import java.math.BigDecimal;

public class LoggedInUser {
    private static Profile loggedInUser;

    public static Profile getInstance() {
        if (loggedInUser == null) {
            create();
        }
        return loggedInUser;
    }

    private static void create() {
        final City city = City.builder()
                .lat(BigDecimal.valueOf(52.412811)) // Solihull
                .lon(BigDecimal.valueOf(-1.778197))
                .build();
        loggedInUser =
                Profile.builder().city(city).build();
    }
}
