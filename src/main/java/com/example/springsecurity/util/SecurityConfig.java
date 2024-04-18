package com.example.springsecurity.util;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    // inmemory userwith roles
    /*@Bean
    public InMemoryUserDetailsManager getUsers() {

        UserDetails user1 = User.builder().username("nishikant").roles("SITEADMIN", "SITEUSER").password("{noop}abc").build();
        UserDetails user2 = User.builder().username("menka").roles("ADMIN", "SITEADMIN").password("{bcrypt}$2a$12$PXHi8ZAW6mQBFhwzk/4GNeQBlwMs2r28lG/xpoqEPcsjKapH5m7ey").build();
        UserDetails user3 = User.withDefaultPasswordEncoder().username("samriddhi").roles("SITEUSER").password("abc").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }*/

    // "web security configurer adapter" class has been depreciated and component based spring security is now in use with lambda dsl
    // method to Authorize a user access to a url based on the role
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers(HttpMethod.GET, "/home/hello")
                        .hasAnyRole("SITEADMIN", "ADMIN", "SITEUSER")
                        .requestMatchers(HttpMethod.GET, "/home/employees")
                        .hasAnyRole("ADMIN"));
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

   /*@Bean
    public DataSource mysqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/employee_directory");
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
    }*/

    // fetch User details using  springboot tables "users and authorities"
   /* @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    // fetch User details using  custom tables "users_nishikant and authorities_nishikant"
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager =  new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select name ,pwd ,activeyn from users_nishikant where name =?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select name ,role from authorities_nishikant where name =?");
        return jdbcUserDetailsManager;
    }


}