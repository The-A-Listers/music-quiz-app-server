package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserProfileRepositoryTests {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Test
    void testFindAll() {
        UserProfile userProfile1 = new UserProfile("1","amjad",true,false);
        userProfileRepository.save(userProfile1);

        UserProfile userProfile2 = new UserProfile("2","bob",false,false);
        userProfileRepository.save(userProfile2);

        Iterable<UserProfile> userProfiles = userProfileRepository.findAll();

        assertThat(userProfiles).hasSize(2);
    }
}
