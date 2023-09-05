package com.example.boot08.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.boot08.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//비밀번호를 암호화 할때 사용되는 객체를 Bean 으로 만들어 주어야 한다 (Spring Security 내부적으로 사용됨)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//spring security 예외 요청 경로 셋팅 
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**", "/hello/token");
	}
	
	//인증 메니저 객체도 Bean 으로 만들어 주어야 한다.
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Autowired
	private JwtFilter jwtFilter;
	
	//Spring Security 세부 setting
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors(); //react 개발환경, 모바일앱 환경에 맞는 설정 
		http.authorizeHttpRequests().antMatchers("/auth").permitAll()   // "/auth" 요청은 인증을 요구하지 않겠다
			.anyRequest().authenticated()
			.and().exceptionHandling()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// JwtFilter 가 동작하도록 등록해 준다. 
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}















