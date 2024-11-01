package org.mhh.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    /*
    //NotRecommended For production => if log in successfully show 403 (Access denied)
    @Bean
    SecurityFilterChain defaultSecurityFilterChainForTest_1(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

*/

 /*
    //NotRecommended For production => all urls can accessible without authentication
    @Bean
    SecurityFilterChain defaultSecurityFilterChainForTest_2(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }
*/

}
