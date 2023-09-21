package com.project.sanatoriumregistry.SanatoriumRegistry.config;

import com.project.sanatoriumregistry.SanatoriumRegistry.service.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.project.sanatoriumregistry.SanatoriumRegistry.constants.SecurityConstants.*;
import static com.project.sanatoriumregistry.SanatoriumRegistry.constants.UserRolesConstants.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(RESOURCES_WHITE_LIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(USERS_WHITE_LIST.toArray(String[]::new)).permitAll()
//                        .requestMatchers(VACATIONERS_PERMISSION_LIST.toArray(String[]::new)).hasRole(REGISTRY)
//                        .requestMatchers(SERVICES_PERMISSION_LIST.toArray(String[]::new)).hasRole(REGISTRY)
//                        .requestMatchers(APPOINTMENTS_PERMISSION_LIST.toArray(String[]::new)).hasRole(REGISTRY)
//                        .requestMatchers(BOOKINGS_PERMISSION_LIST.toArray(String[]::new)).hasRole(REGISTRY)
//                        .requestMatchers(SERVICES_ADMIN_LIST.toArray(String[]::new)).hasRole(ADMIN)
//                        .requestMatchers(USERS_ADMIN_LIST.toArray(String[]::new)).hasRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                );

        return httpSecurity.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}

