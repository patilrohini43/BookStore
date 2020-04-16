package com.bridgelabz.bookstore.configure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories("com.bridgelabz.bookstore.repository")
public class ApplicationConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}