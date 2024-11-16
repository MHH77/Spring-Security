package org.mhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true) // do not recommend for production environment
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
