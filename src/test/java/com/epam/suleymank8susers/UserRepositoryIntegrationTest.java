package com.epam.suleymank8susers;


import com.epam.suleymank8susers.model.User;
import com.epam.suleymank8susers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = UserRepositoryIntegrationTest.DatabaseInitializer.class)

public class UserRepositoryIntegrationTest {

    @Container
    private static final PostgreSQLContainer database = new PostgreSQLContainer("postgres:latest");

    public static class DatabaseInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment( applicationContext ,
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword());
        }
    }

    @Autowired
    UserRepository userRepository;

    @Test
    void userAddTest(){
        User user1 = new User();
        user1.setUsername("user-1");
        user1.setAmountOfPosts(4);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user-2");
        user2.setAmountOfPosts(5);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user-3");
        user3.setAmountOfPosts(6);
        userRepository.save(user3);

        assertEquals(3 , userRepository.findAll().size());

    }
}
