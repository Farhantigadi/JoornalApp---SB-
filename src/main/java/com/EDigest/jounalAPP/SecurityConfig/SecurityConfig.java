package com.EDigest.jounalAPP.SecurityConfig;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


















@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/helo")
                        .permitAll()

                        .anyRequest().authenticated()
                )

                .formLogin(withDefaults()); // ✅ Use default login form


//                .formLogin(form -> form      // ✅ Replaces deprecated formLogin()
//                        .loginPage("login")     // (Optional) your custom login page
//                        .permitAll()
//                );

        return http.build();
    }
}


