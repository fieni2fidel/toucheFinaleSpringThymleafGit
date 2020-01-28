package com.group.touchefinale;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		
	}
	
}
