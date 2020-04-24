package com.bridgelabz.bookstore.configure;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.unit.DataSize;

import javax.persistence.EntityManagerFactory;
import javax.servlet.MultipartConfigElement;

@Configuration
@EnableJpaRepositories("com.bridgelabz.bookstore.repository")
public class ApplicationConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}