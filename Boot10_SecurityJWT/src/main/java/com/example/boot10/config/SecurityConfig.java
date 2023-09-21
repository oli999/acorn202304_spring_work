package com.example.boot10.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import com.example.boot10.filter.JwtFilter;
import com.example.boot10.handler.AuthFailHandler;
import com.example.boot10.handler.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired 
	private JwtFilter jwtFilter;
	
	//jwt 를 쿠키로 저장할때 쿠키의 이름
	@Value("${jwt.name}")
	private String jwtName;
	
	// favicon.io 가 로그인후 자동 리다일렉트 될때 오류를 일으키기 때문에 미리 Spring Security 에서 배제를 시킨다. 
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
	}
	
	//SecurityFilterChain 을 bean 으로 만들어준다.
	// Bean 으로 등록된 AuthSuccessHandler, AuthFailHandler 로 전달 받아서 사용한다. 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthSuccessHandler successHandler, AuthFailHandler failHandler, 
			CookieRequestCache cookCache) throws Exception{
		
		httpSecurity
			.csrf().disable()
			.authorizeHttpRequests(config->
				config
					.antMatchers("/", "/users/loginform").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
					.anyRequest().authenticated()
			)
			.formLogin(config->
				config
					.loginPage("/users/loginform")
					.loginProcessingUrl("/login")
					.usernameParameter("userName")
					.passwordParameter("password")
					.successHandler(successHandler)
					.failureHandler(failHandler)
			)
			.logout(config->
				config
					.logoutUrl("/users/logout")
					.logoutSuccessUrl("/")
					.deleteCookies(jwtName) //로그아웃시  jwt 가 저장된 쿠키 삭제하기 
			)
			//세션을 사용하지 않도록 설정한다.
			.sessionManagement(config->config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			//토큰을 검사하는 필터를 UsernamePasswordAuthenticationFilter 가 동작하기 이전에 동작하도록 설정 한다.
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			//세션을 사용할수 없기때문에 쿠키케시를 사용하도록 설정한다. 
			.requestCache(config->config.requestCache(cookCache));
																
		return httpSecurity.build();
	}
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

	//인증 메니저 객체를 bean 으로 만든다.
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	//쿠키 케시를 bean 으로 만든다. 
	@Bean
	public CookieRequestCache getCookieRequestCache() {
		return new CookieRequestCache();
	}
}









