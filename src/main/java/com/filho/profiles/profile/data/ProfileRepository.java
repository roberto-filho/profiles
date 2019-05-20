package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, UUID>, JpaSpecificationExecutor {

    @Query(value = "SELECT DISTINCT religion FROM Profile", nativeQuery = true)
    List<String> findDistinctReligions();
}
