package com.tom.test.configurations;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tom on 6/7/2016.
 */

@Configuration
@EnableJpaRepositories("com.tom.test.repositories")
@ComponentScan
public class CommonConfig extends WebMvcAutoConfiguration{

    @Bean
    public StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }
}
