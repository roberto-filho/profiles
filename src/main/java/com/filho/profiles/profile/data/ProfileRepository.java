package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, UUID>, JpaSpecificationExecutor {

    @Query(value = "SELECT DISTINCT p.religion FROM Profile p")
    List<String> findDistinctReligions();

    @Query(value = "SELECT DISTINCT p.jobTitle FROM Profile p")
    List<String> findDistinctJobTitles();
}
