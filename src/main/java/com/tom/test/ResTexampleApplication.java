package com.tom.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching

//public class ResTexampleApplication extends SpringBootServletInitializer{
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(ResTexampleApplication.class);
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(ResTexampleApplication.class, args);
//	}
//}



public class ResTexampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResTexampleApplication.class, args);
	}
}

