package com.EDigest.jounalAPP.SecurityConfig;



import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        // Keep ROLE_ prefix exactly as stored in DB
        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new) // No string manipulation!
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}