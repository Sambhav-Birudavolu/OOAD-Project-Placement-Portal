package com.pesuplacements.config;

import com.pesuplacements.services.CustomCompanyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomCompanyDetailsService customCompanyDetailsService;

    @Bean
    public SecurityFilterChain companySecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/companylogin", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/companylogin") 
                .loginProcessingUrl("/companylogin") 
                .defaultSuccessUrl("/companydashboard", true) 
                .permitAll() 
            )
            .logout(logout -> logout
                .logoutUrl("/logout") 
                .logoutSuccessUrl("/") 
            )
            .authenticationProvider(companyAuthProvider()) 
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }

    
    @Bean
    public AuthenticationProvider companyAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customCompanyDetailsService); 
        provider.setPasswordEncoder(new BCryptPasswordEncoder()); 
        return provider;
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
