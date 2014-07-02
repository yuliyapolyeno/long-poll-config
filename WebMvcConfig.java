package com.bedgebury.presentation.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan (basePackages = { "com.bedgebury.presentation" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Log log = LogFactory.getLog(WebMvcConfig.class);

	@Override
	public void configureAsyncSupport (AsyncSupportConfigurer configurer) {
		
		configurer.setDefaultTimeout(30 * 1000L);
//		configurer.registerDeferredResultInterceptors(interceptors)
	}

	public void addViewControllers (ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("teledetailer");
	}

	@Override
	public void addResourceHandlers (ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

	@Bean
	public ViewResolver viewResolver () {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void addReturnValueHandlers (List<HandlerMethodReturnValueHandler> returnValueHandlers) {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new StringHttpMessageConverter());
    messageConverters.add(new MappingJackson2HttpMessageConverter());
    returnValueHandlers.add(new RequestResponseBodyMethodProcessor(messageConverters));
		super.addReturnValueHandlers(returnValueHandlers);
	}

}