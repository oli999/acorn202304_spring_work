package com.example.boot08.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//이설정이 정상 동작하도록 어노테이션을 붙인다.
@Configuration
public class WebConfig implements WebMvcConfigurer{
	/*
	 *  Cross Origin Resource Sharing  ( CORS ) 설정 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 경로에 대해 CORS 설정을 적용
        .allowedOrigins("http://localhost:3000") // 모든 오리진 허용, 필요한 경우 특정 오리진을 지정할 수도 있음
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // 허용할 HTTP 메서드 지정
        .allowedHeaders("*") // 모든 헤더 허용
        .allowCredentials(true) // 인증 정보 허용
        .maxAge(3600); // preflight 요청 결과를 캐시하는 시간 (1시간)
	}
}
