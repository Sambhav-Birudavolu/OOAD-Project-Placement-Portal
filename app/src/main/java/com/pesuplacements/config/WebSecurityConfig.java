package com.pesuplacements.config;

import com.pesuplacements.services.CustomStudentDetailsService;
import com.pesuplacements.services.CustomAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig {

        @Autowired
        private CustomAdminDetailsService customAdminDetailsService;

        @Bean
        public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
            http
                .securityMatcher("/admin/**", "/adminlogin")
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/adminlogin", "/css/**", "/js/**","/api/**").permitAll()
                    .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
                    .loginPage("/adminlogin")
                    .loginProcessingUrl("/adminlogin")
                    .defaultSuccessUrl("/admindashboard", true)
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/adminlogout")
                    .logoutSuccessUrl("/")
                )
                .authenticationProvider(adminAuthProvider())
                .csrf(csrf -> csrf.disable());

            return http.build();
        }

        @Bean
        public AuthenticationProvider adminAuthProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(customAdminDetailsService);
            provider.setPasswordEncoder(new BCryptPasswordEncoder());
            return provider;
        }
    }

    @Configuration
    @Order(2)
    public static class StudentSecurityConfig {

        @Autowired
        private CustomStudentDetailsService customStudentDetailsService;

        @Bean
        public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/studentlogin", "/css/**", "/js/**", "/images/**").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/studentlogin")
                    .loginProcessingUrl("/studentlogin")
                    .defaultSuccessUrl("/studentdashboard", true)
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                )
                .authenticationProvider(studentAuthProvider())
                .csrf(csrf -> csrf.disable());

            return http.build();
        }

        @Bean
        public AuthenticationProvider studentAuthProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(customStudentDetailsService);
            provider.setPasswordEncoder(new BCryptPasswordEncoder());
            return provider;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
