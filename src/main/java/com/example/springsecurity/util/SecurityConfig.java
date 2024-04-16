package com.example.springsecurity.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager getUsers() {

        UserDetails user1 = User.builder().username("nishikant").roles("siteadmin", "siteuser").password("{noop}abc").build();
        UserDetails user2 = User.builder().username("menka").roles("admin", "siteadmin").password("{bcrypt}$2a$12$PXHi8ZAW6mQBFhwzk/4GNeQBlwMs2r28lG/xpoqEPcsjKapH5m7ey").build();
        UserDetails user3 = User.withDefaultPasswordEncoder().username("samriddhi").roles("siteuser").password("abc").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }


    // "web security configurer adapter" class has been depreciated and component based spring security is now in use with lambda dsl
    // method to Authorize a user access to a url based on the role
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers(HttpMethod.GET, "/home/hello")
                        .hasAnyRole("siteadmin", "admin", "siteuser")
                        .requestMatchers(HttpMethod.GET, "/home/employees")
                        .hasAnyRole("siteuser"));
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
