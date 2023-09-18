package com.example.boot09.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot09.dto.LoginRequest;
import com.example.boot09.repository.service.CustomUserDetailsService;

@Controller
public class UserController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailsService service;
	
	
	@GetMapping("/users/loginform")
	public String loginform() {
		
		return "users/loginform";
	}
	@GetMapping("/users/required_loginform")
	public String required_loginform() {
		
		return "users/required_loginform";
	}
	@GetMapping("/users/required_login")
	public String required_login() {
		
		return "users/required_login";
	}

	@PostMapping("/users/login")
	public String login(LoginRequest dto, HttpServletRequest request, SavedRequest savedRequest) throws Exception {
		System.out.println(savedRequest.getRequestURI());
		
		try {
			//입력한 id 비밀번호를 토큰에 담아서 
			UsernamePasswordAuthenticationToken token=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
			//인증 메니저 객체를 이용해서 인증 작업을 진행한다. 
			authManager.authenticate(token);
		}catch(BadCredentialsException bce) {
			bce.printStackTrace();
			return "users/login";
		}
		
		UserDetails userDetails=service.loadUserByUsername(dto.getUserName());
		//토큰 객체 생성
		UsernamePasswordAuthenticationToken authToken=
				new UsernamePasswordAuthenticationToken
				(userDetails, null, new ArrayList<GrantedAuthority>());
		//HttpServletRequest 에 담겨진 세부 정보를 인증토큰에 담아서 요청한 클라이언트의 ip 정보 요청 url, 세션등의 정보를 추적할수 있도록 한다.
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		//위에서 만들어진 인증토큰을 SecurityContext 에 등록합니다. 
		SecurityContextHolder.getContext().setAuthentication(authToken);
			
		
		return "users/login";
	}
	
}












