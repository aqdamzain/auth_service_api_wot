package com.noir.webthingauth;

import com.noir.webthingauth.filters.UserAuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebthingauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebthingauthApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<UserAuthFilter> userRegistrationBean(){
		FilterRegistrationBean<UserAuthFilter> registrationBean = new FilterRegistrationBean<>();
		UserAuthFilter userAuthFilter = new UserAuthFilter();
		registrationBean.setFilter(userAuthFilter);
		registrationBean.addUrlPatterns("/api/things/*");
		return registrationBean;
	}

}
