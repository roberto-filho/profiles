package com.filho.profiles.profile.data;

import com.filho.profiles.profile.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ProfileDao extends PagingAndSortingRepository<Profile, UUID> {
}
