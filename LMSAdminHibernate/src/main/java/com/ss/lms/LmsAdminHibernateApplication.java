package com.ss.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class LmsAdminHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsAdminHibernateApplication.class, args);
	}

}
