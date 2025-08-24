package com.nikhil.journal_app.service;

import com.nikhil.journal_app.entity.User;
import com.nikhil.journal_app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername(){
        User actualUser = userRepository.findByUserName("nikhil");
        Assertions.assertNotNull(actualUser, "User is null!");
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "10,-1,9"
    })
    public void testParameterized(int a, int b, int expected){
        Assertions.assertEquals(expected, a + b);
    }
}
