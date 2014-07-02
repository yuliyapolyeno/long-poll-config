package com.bedgebury.presentation.config;

import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses () {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class, PresentationConfig.class, SecurityConfig.class, EmailConfig.class };
	}
		
	@Override
	protected String[] getServletMappings () {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setAsyncSupported(true);
	}

	@Override
	protected boolean isAsyncSupported () {
		return true;
	}
		
}