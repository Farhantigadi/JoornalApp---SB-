package com.EDigest.jounalAPP.service;

import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.Repo.UserRepo;
import com.EDigest.jounalAPP.SecurityConfig.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoadUserDetailsTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo repo;

    /*
    Used to Initialize the mocks
    of this class
     */

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        // Arrange
        User user = new User();
        user.setUsername("farhan");
        user.setPassword("pass123");
        user.setRoles(List.of("USER"));

        when(repo.findByUsername("farhan")).thenReturn(user);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("farhan");

        // Assert
        assertNotNull(userDetails);
        assertEquals("farhan", userDetails.getUsername());
        assertEquals("pass123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("USER")));
    }
}
