package org.mhh.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //spring security by default block all the post and put and other http method that change data in backend or database by csrf
        http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/myAccount", "/myLoans", "/myCards", "/myBalance").authenticated()
                .requestMatchers("/contract", "/notices","/user/register","/actuator/**").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //means hashing password
        return new BCryptPasswordEncoder();
    }

}
