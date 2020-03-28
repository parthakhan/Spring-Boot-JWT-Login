package com.partha.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ComponentScan(basePackages = "com.partha.library")
@SpringBootApplication 
public class LibraryApplication  {
		
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LibraryApplication.class, args);
	}
	
	

	
}
