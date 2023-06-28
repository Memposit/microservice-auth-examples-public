package com.memposit.example.mapia.auth.configuration;

import com.memposit.example.mapia.auth.RestAuthenticationEntryPoint;
import com.memposit.example.mapia.auth.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration("authCommonSecurityConfig")
public class AuthCommonWebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Order(1)
    @Configuration("commonJwtSecurityConfig")
    @RequiredArgsConstructor
    public static class CommonJwtSecurityConfig extends WebSecurityConfigurerAdapter {

        private final RestAuthenticationEntryPoint authenticationEntryPoint;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/login")
                    .cors()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable()
                    .httpBasic()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    // 150 > 100 (default order). It means, that in clients we have possibility to configure more specific rules or override common one
    // see com.memposit.example.mapia.gateway.configuration.ApiWebSecurityConfig
    @Order(150)
    @Configuration("commonSecurityConfig")
    @RequiredArgsConstructor
    public static class CommonSecurityConfig extends WebSecurityConfigurerAdapter {

        private final RestAuthenticationEntryPoint authenticationEntryPoint;
        private final JwtAuthorizationFilter jwtAuthorizationFilter;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable()
                    .formLogin()
                    .disable()
                    .httpBasic()
                    .disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests().anyRequest().authenticated();
        }
    }
}

