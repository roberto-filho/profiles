package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, UUID>, JpaSpecificationExecutor {

}
