package com.bedgebury.presentation.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bedgebury.presentation.config.validator.RuntimeEnvironmentPropertiesConfigurer;
import com.bedgebury.presentation.service.EmailDataReader;
import com.bedgebury.presentation.service.EmailDataWriter;
import com.bedgebury.presentation.service.PresentationsDataReader;

@Configuration
public class PresentationConfig {

	@Bean
	public RuntimeEnvironmentPropertiesConfigurer strictRuntimeEnvironmentPropertiesConfigurer () {

		RuntimeEnvironmentPropertiesConfigurer propertyPlaceholderConfigurer = new RuntimeEnvironmentPropertiesConfigurer();

		propertyPlaceholderConfigurer.setPropertyLocations(Collections.<Resource> singleton(new ClassPathResource("properties/")));
		propertyPlaceholderConfigurer.setDefaultProperties("application.properties");

		return propertyPlaceholderConfigurer;
	}
	
	@Bean
	public EmailDataReader emailDataReader (@Value("${participants.path}") String path) {
		System.out.println("creating emailDataReader !!!");
		return new EmailDataReader(path);
	}

	@Bean
	public EmailDataWriter emailDataWriter (@Value("${participants.path}") String path) {
		System.out.println("creating emailDataWriter !!!");
		return new EmailDataWriter(path);
	}
	
	@Bean
	public PresentationsDataReader presentationsDataReader(@Value("${presentations.path}") String path) {
		System.out.println("creating presentationsDataReader !!!");
		return new PresentationsDataReader(path);
	}
	
}
