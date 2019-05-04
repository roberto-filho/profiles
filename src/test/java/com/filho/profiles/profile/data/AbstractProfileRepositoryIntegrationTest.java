package com.filho.profiles.profile.data;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractProfileRepositoryIntegrationTest {
    static final PageRequest PAGE_REQUEST = PageRequest.of(0, 25);

    @Autowired
    private ProfileRepository repository;

    @Autowired
    ProfileRepositoryExt profileRepositoryExt;

    @Before
    public void setUp() {
        repository.deleteAll();
        createDataForTest(repository);
    }

    public ProfileRepository getRepository() {
        return repository;
    }

    public ProfileRepositoryExt getProfileRepositoryExt() {
        return profileRepositoryExt;
    }

    protected abstract void createDataForTest(ProfileRepository repository);
}
