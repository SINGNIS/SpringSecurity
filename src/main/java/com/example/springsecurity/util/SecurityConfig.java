package com.example.springsecurity.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager getUsers() {

        UserDetails user1 = User.builder().username("nishikant").password("{noop}abc").build();
        UserDetails user2 = User.builder().username("singh").password("{bcrypt}$2a$12$PXHi8ZAW6mQBFhwzk/4GNeQBlwMs2r28lG/xpoqEPcsjKapH5m7ey").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
