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
		//h2 DB 요청경로는 Spring Security 예외
		return (web) -> web.ignoring().antMatchers("/h2-console/**");
	}
	
	//SecurityFilterChain 을 bean 으로 만들어준다.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//서버 시작시에 HttpSecurity 객체가 메소드의 인자로 전달되는데 해당 객체를 이용해서 Security 관련 설정을 하면 된다.
		httpSecurity					
		    .csrf().disable() //csrf 사용하지 않기 
			.authorizeHttpRequests(config-> //인증요청 설정
				config
					//인증은 하지만 pass 시킬 경로 지정 (root , 로그인폼, 로그인이 풀렸을 경우)
					.antMatchers("/","/users/loginform","/users/expired").permitAll()
					// ADMIN role 을 가지고 있어야 요청할수 있는 경로 설정
					.antMatchers("/admin/**").hasRole("ADMIN")
					// ADMIN or STAFF role 을 가지고 있어야 요청할수 있는 경로 설정
					.antMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
					// 위에 명시한 요청경로 이외의 모든 요청 경로도 인증을 거치도록 설정한다. 
					.anyRequest().authenticated()	
			)
			.formLogin(config-> //폼 로그인 설정 
				config
					//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정 
					.loginPage("/users/required_loginform")	
					//Spring Security 가 자동으로 로그인 처리를 해줄 요청경로 설정
					.loginProcessingUrl("/login")
					//자동으로 로그인 처리를 해주기 위해서는 사용자명과 비밀번호의 파라미터명을 알려 주어야한다. 
					// <input type="text" name="userName"/>
					// <input type="password"  name="password"/> 
					//폼 전송인경우 name 속성의 value 값과 일치 시키면 된다. 
					.usernameParameter("userName")
					.passwordParameter("password")
					// 로그인 성공이후에 무언가 처리 할게 있으면 핸들러를 등록해서 처리한다.
					.successHandler(new AuthSuccessHandler())
					// 로그인 실패(아이디 혹은 비밀번호 불일치)인 경우 forward 이동할 경로 설정
					.failureForwardUrl("/users/login_fail")
					//설정에서 지정한 경로도 인증없이 요청가능하도록 설정한다. 
					.permitAll()
			)
			.logout(config-> //로그아웃 설정
				config
					//Spring Security 가 자동으로 로그아웃 처리 해줄 경로 설정
					.logoutUrl("/users/logout")
					//로그 아웃 이후에 리다일렉스 시킬 경로 설정
					.logoutSuccessUrl("/")
					//설정에서 지정한  경로도 인증없이 요청 가능하도록 설정한다. 
					.permitAll()
			)
			.exceptionHandling(config-> //인증 처리중 예외가 발생했을때의 설정
				//403 forbidden 인 경우 리다일렉트 시킬 경로 설정 
				config.accessDeniedPage("/users/denied")
				
			)
			.sessionManagement(config-> //세션관리
				config
					.maximumSessions(1) //최대 허용 세션 갯수
					.expiredUrl("/users/expired") //허용 세션 갯수가 넘어서 로그인 해제된 경우 리다일렉트 
			);
														
		return httpSecurity.build();
	}
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	//인증 메니저 객체를 bean 으로 만든다. (Spring Security 가 자동 로그인 처리할때도 사용되는 객체)
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












