package com.tom.test.configurations;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.server.web.WebServlet;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tom on 6/7/2016.
 */

@Configuration
@EnableJpaRepositories("com.tom.test.repositories")
public class CommonConfig {

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
