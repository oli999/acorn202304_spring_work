package com.example.boot09.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.boot09.handler.AuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//Spring Security 를 거치지 않을 요청 경로 설정 
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/h2-console/**");
	}
	
	//SecurityFilterChain 을 bean 으로 만들어준다.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//서버 시작시에 HttpSecurity 객체가 메소드의 인자로 전달되는데 해당 객체를 이용해서 Security 관련 설정을 하면 된다.
		httpSecurity					
		    .csrf().disable()
			.authorizeHttpRequests(config->
				config
					.antMatchers("/","/users/loginform").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.anyRequest().authenticated()		
			)
			.formLogin(config->
				config
				.loginPage("/users/required_loginform")	
				.loginProcessingUrl("/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler())
				.failureForwardUrl("/users/login_fail")
				.permitAll()
			)
			.logout(config->
				config
					.logoutUrl("/users/logout")
					.logoutSuccessUrl("/")
					.permitAll()
			);
																
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

}












