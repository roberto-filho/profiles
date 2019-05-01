package com.filho.profiles;

import com.filho.profiles.profile.Profile;
import com.filho.profiles.profile.data.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfilesApplicationTests {

	@Autowired
	ProfileRepository dao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldInsertAndFindProfile() {
		final Profile p = Profile.builder().age(1).build();
		final Profile saved = dao.save(p);

		final Optional<Profile> found = dao.findById(saved.getId());

		assertThat(found.isPresent()).isTrue();
	}
}
