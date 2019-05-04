package com.filho.profiles.profile.data;

import com.filho.profiles.dto.ProfileFilter;
import com.filho.profiles.profile.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsContactIntegrationTest {

    private static final PageRequest PAGE_REQUEST = PageRequest.of(0, 25);

    @Autowired
    ProfileRepository repository;

    @Autowired
    ProfileRepositoryExt repositoryExt;

    @Before
    public void setUp() {
        repository.deleteAll();
        createProfilesWithContactAndWithoutContacts();
    }

    @Test
    public void shouldFindProfileWithContactsWhenIsContactIsTrue() {
        final ProfileFilter filter = ProfileFilter.builder()
                .hasContact(true)
                .build();

        final Page<Profile> queryResults = repositoryExt.findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(1)
                .allMatch(Profile::hasContacts);
    }

    @Test
    public void shouldFindProfile0orNullContactsWhenIsContactIsFalse() {
        final ProfileFilter filter = ProfileFilter.builder()
                .hasContact(false)
                .build();

        final Page<Profile> queryResults = repositoryExt.findByCriteria(filter, PAGE_REQUEST);

        assertThat(queryResults)
                .hasSize(2)
                .allMatch(Profile::hasNoContacts);
    }

    private void createProfilesWithContactAndWithoutContacts() {
        final Profile withContacts = Profile.builder()
                .displayName("I have contacts")
                .contactsExchanged(3)
                .build();

        final Profile withNoContacts = Profile.builder()
                .displayName("I have no contacts")
                .contactsExchanged(0)
                .build();

        final Profile withNullContacts = Profile.builder()
                .displayName("I have null contacts")
                .contactsExchanged(null)
                .build();

        repository.saveAll(Arrays.asList(withContacts, withNoContacts, withNullContacts));
    }
}
