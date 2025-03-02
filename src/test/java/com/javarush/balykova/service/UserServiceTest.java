package com.javarush.balykova.service;

import com.javarush.balykova.entity.User;
import com.javarush.balykova.exception.AppException;
import com.javarush.balykova.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testCreateNewUser() {
        User user = User.builder().login("testUser").build();

        when(userRepository.find(any())).thenReturn(Stream.empty());

        userService.create(user);

        verify(userRepository).create(user);
    }

    @Test
    public void testCreateExistingUserThrowsException() {
        User user = User.builder().login("existingUser").build();
        User existingUser = User.builder().login("existingUser").build();

        when(userRepository.find(any())).thenReturn(Stream.of(existingUser));

        AppException exception = assertThrows(AppException.class, () -> userService.create(user));
        assertEquals("User with login existingUser already exists", exception.getMessage());

        verify(userRepository, never()).create(any());
    }
}