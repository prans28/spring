package com.te.jspiders.security.config;

import com.te.jspiders.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityFilter securityFilter;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeHttpRequests()
            .antMatchers("/api/employee/update","/api/employee/changePassword")
            .hasAnyRole("EMPLOYEE","ADMIN");
        http.authorizeHttpRequests()
            .antMatchers("/api/employee/delete")
            .hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/api/student/**").hasRole("STUDENT");
        http.authorizeHttpRequests().antMatchers("/api/traineer/**").hasRole("TRAINEER");
        http.authorizeHttpRequests().antMatchers("/public/**")
                .permitAll().anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
