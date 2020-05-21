package com.microservice.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	SlackApiConfiguration slackApiConfiguration;


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//本番環境
		//registry.addResourceHandler("/images/**").addResourceLocations("file:/var/www/html/img/");

		//ローカル環境
		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:\\img\\profile\\");

	}
}