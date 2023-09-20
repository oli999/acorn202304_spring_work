package com.example.boot10.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.boot10.filter.JwtFilter;
import com.example.boot10.handler.AuthFailHandler;
import com.example.boot10.handler.AuthSuccessHandler;
/*
 *  [ 참고 ]
 *  
 *  .csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/about").permitAll()
                .antMatchers("/accounts").hasRole("ADMINISTRATOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .requestCache().requestCache(new CookieRequestCache())
                .and()
                .logout()
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext()
                .securityContextRepository(securityContextRepository);
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired JwtFilter jwtFilter;
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
	}
	
	//SecurityFilterChain 을 bean 으로 만들어준다.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthSuccessHandler successHandler, AuthFailHandler failHandler) throws Exception{
		//서버 시작시에 HttpSecurity 객체가 메소드의 인자로 전달되는데 해당 객체를 이용해서 Security 관련 설정을 하면 된다.
		SecurityFilterChain chain=httpSecurity
			.httpBasic(s->s.disable())
			.csrf(s->s.disable())
			.authorizeHttpRequests(s->{
				s.antMatchers("/", "/users/loginform", "/users/login").permitAll();
				
				//s.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN");
				s.antMatchers("/admin/**").hasRole("ADMIN");
				s.antMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF");
				
				s.anyRequest().authenticated();
				
			})
			.formLogin(s->{
				s.loginPage("/users/loginform");
				s.loginProcessingUrl("/login");
				s.usernameParameter("userName");
				s.passwordParameter("password");
				s.successHandler(successHandler);
				s.failureHandler(failHandler);
			})
			.logout(s->{
				s.logoutUrl("/users/logout");
				s.logoutSuccessUrl("/");
				s.deleteCookies("jwtToken");
			})
			.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.requestCache(s->s.requestCache(getCookieRequestCache()))
			.build();
																
		return chain;
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
	
	@Bean
	public CookieRequestCache getCookieRequestCache() {
		return new CookieRequestCache();
	}
}









