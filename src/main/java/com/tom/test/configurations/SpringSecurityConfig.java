package com.tom.test.configurations;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * Created by tom on 8/9/2016.
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor encryptor){
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(encryptor);
        return passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/store").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/cart").permitAll()
                .antMatchers("/generatedummydata").permitAll()
                .antMatchers("/customer/**").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .antMatchers("/framents/**").permitAll()
                .antMatchers("/bundle/**").hasAnyAuthority("ADMIN")
                .antMatchers("/developer/**").hasAnyAuthority("ADMIN")
                .antMatchers("/product/**").hasAnyAuthority("ADMIN")
                .antMatchers("/publisher/**").hasAnyAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll().logoutUrl("/logout")
                .deleteCookies("my-remember-me")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/store")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied")
                .and()
                .rememberMe()
                .tokenValiditySeconds(2678400);
    }


}
