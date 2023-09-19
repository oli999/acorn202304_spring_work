package com.example.boot10.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot10.dto.LoginRequest;
import com.example.boot10.service.CustomUserDetailsService;
import com.example.boot10.util.JwtUtil;


@Controller
public class UserController {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService service;
	
	//ADMIN 만 요청할수 있는 경로 
	@GetMapping("/admin/user_delete")
	public String userDelete() {
		
		return "users/user_delete";
	}
	
	//ADMIN or STAFF 만 요청할수 있는 경로 
	@GetMapping("/staff/user_list")
	public String userList() {
		
		return "users/user_list";
	}
	
	
	@GetMapping("/users/loginform")
	public String loginform() {
		
		return "users/loginform";
	}
	
	@PostMapping("/users/login_fail")
	public String loginFail() {
		
		return "users/login_fail";
	}
	
	@PostMapping("/users/login_success")
	public String loginSuccess() throws Exception {
	
		return "users/login_success";
	}
	
}












