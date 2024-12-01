package org.mhh.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.mhh.Filters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        //store all credential by framework automatically
        http.securityContext().requireExplicitSave(false)
                // when using separate UI application , I want to just login once note when every request comes form ui application have to log in again
                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                        corsConfiguration.setMaxAge(3600L);
                        return corsConfiguration;
                    }
                }).and().csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contract", "/notices", "/user/register", "/actuator/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterBefore(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)  // ابتدا JWTTokenGeneratorFilter را اضافه کنید
                .addFilterBefore(new AuthoritiesLoiteringFilter(), JWTTokenGeneratorFilter.class)  // سپس AuthoritiesLoiteringFilter را اضافه کنید
                .addFilterAfter(new ValidationFilter(), BasicAuthenticationFilter.class)  // فیلتر ValidationFilter باید بعد از BasicAuthenticationFilter باشد
                .addFilterAt(new AuthenticationLoggingAtFilter(), AuthoritiesLoiteringFilter.class)  // اگر می‌خواهید فیلتر بعد از AuthoritiesLoiteringFilter بیاید
                .authorizeHttpRequests()
                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myLoans").authenticated()
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/contract", "/notices", "/user/register", "/actuator/**").permitAll()
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
