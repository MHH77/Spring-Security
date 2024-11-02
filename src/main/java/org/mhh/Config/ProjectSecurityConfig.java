package org.mhh.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/myAccount", "/myLoans", "/myCards", "/myBalance").authenticated()
                .requestMatchers("/contract", "/notices").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
/*
    //Approach 1
    //NotRecommended For production
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ali")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1111")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

*/
        // Approach 2
        UserDetails admin = User.withUsername("ali")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("1111")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //means password as a plain text
        return NoOpPasswordEncoder.getInstance();
    }

}
