package com.bedgebury.presentation.config;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

  @Bean
  public JavaMailSenderImpl mailSender(@Value("${smtp.host}") String host,
                               @Value("${smtp.port}") int port,
                               @Value("${smtp.username}") String username,
                               @Value("${smtp.password}") String password,
                               @Value("${smtp.protocol}") String protocol) {

      Properties properties = new Properties();
      properties.setProperty("mail.smtp.starttls.enable", "true");
      properties.setProperty("mail.smtp.timeout", "25000");

      JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
      javaMailSender.setHost(host);
      javaMailSender.setPort(port);
      javaMailSender.setUsername(username);
      javaMailSender.setPassword(password);
      javaMailSender.setProtocol(protocol);
      javaMailSender.setJavaMailProperties(properties);

      return javaMailSender;
  }

  @Bean
  public VelocityEngine  velocityEngine() {
  	VelocityEngine velocity = new VelocityEngine();

  	velocity.setProperty("resource.loader", "webapp");
  	velocity.setProperty("webapp.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
  	velocity.setProperty("webapp.resource.loader.path", "/WEB-INF/email-templates/");
  	
  	return velocity;
  }
  
	
}
