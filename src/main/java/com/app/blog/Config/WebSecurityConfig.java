package com.app.blog.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.blog.Security.CustomUserDetailService;
import com.app.blog.Security.JWT.JWTAuthenticationEntryPoint;
import com.app.blog.Security.JWT.JWTAuthenticationFilter;
import com.app.blog.Utils.AppConstants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private static final String[] ADMIN_WHITE_LIST_URLS = AppConstants.ADMIN_WHITE_LIST_URLS;
    private static final String[] NORMAL_WHITE_LIST_URLS = AppConstants.NORMAL_WHITE_LIST_URLS;
    private static final String[] ACCESS_WHITE_LIST_URLS = AppConstants.ACCESSABLE_WHITE_LIST_URLS;

    private final CustomUserDetailService customUserDetailService;

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests()
                .antMatchers(ADMIN_WHITE_LIST_URLS).hasRole(AppConstants.ADMIN)
                .antMatchers(NORMAL_WHITE_LIST_URLS).hasRole(AppConstants.NORMAL)
                .antMatchers(ACCESS_WHITE_LIST_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling(handling -> handling.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authenticationProvider(daoAuthenticationProvider());

        DefaultSecurityFilterChain build = http.build();
        return build;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
