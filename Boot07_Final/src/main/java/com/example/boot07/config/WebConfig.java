package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot07.interceptor.LoginInterceptor;



/*
 * 	[ Spring MVC 관련 설정 ]
 *  
 *  1. WebMvcConfigurer 인터페이스를 구현한다.
 *  2. 설정에 필요한 메소드만 오버라이딩한다. 
 *     주로 Resource Handler , Interceptor , view page 관련 설정을 여기서 한다.
 *  3. 설정에 관련된 클래스에는 @Configuration 어노테이션을 붙여야한다. 
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//LoginInterceptor DI
	@Autowired LoginInterceptor loginInter;
	
	//Interceptor 를 추가할때 오버라이드 하는 메소드 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//메소드의 인자로 전달되는 InteceptorRegistry 객체를 이용해서 Interceptor 를 등록하면 된다.
		registry.addInterceptor(loginInter)
			.addPathPatterns("/users/*","/cafe/*","/file/*")
			.excludePathPatterns("/users/loginform", "/users/login", 
					"/users/signup_form", "/users/signup",
					"/cafe/list","/cafe/detail","/cafe/ajax_comment_list",
					"/file/list", "/file/download");
	}
	
	// webapp/resources 폴더 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}

















