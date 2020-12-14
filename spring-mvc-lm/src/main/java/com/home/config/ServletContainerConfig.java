package com.home.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletContainerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;

	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { DispatcherServletConfiguration.class };

	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };

	}

}
