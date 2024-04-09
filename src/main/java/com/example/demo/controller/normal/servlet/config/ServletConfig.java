package com.example.demo.controller.normal.servlet.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.normal.servlet.RegistrationServlet;

@Configuration
public class ServletConfig {

	@Bean
	ServletRegistrationBean<RegistrationServlet> regServlet(){
		ServletRegistrationBean<RegistrationServlet> serv = new ServletRegistrationBean<RegistrationServlet>
		(new RegistrationServlet(),"/RegistrationPage");
		return serv;
		
	}
}
