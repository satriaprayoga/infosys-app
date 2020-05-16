package com.infosys.destination.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	//@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTION")
//        .allowedOrigins("*");
//	}
}
