package com.example.dashboard.start;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class StartApplicationTests {

    @Test
    @Disabled
    void contextLoads() {
    }

    @Test
    public void testUserData() {

        List<String> users = getUserData();
        assertThat(users)
                .containsAll(expectedUsers());
    }

    private List<String> getUserData() {
        return List.of("Santosh", "Rahul","Deepak");
    }

    private List<String> expectedUsers() {
        return List.of("Santosh", "Rahul");
    }
}
