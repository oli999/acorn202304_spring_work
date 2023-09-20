package com.example.boot09.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot09.dto.LoginRequest;
import com.example.boot09.repository.service.CustomUserDetailsService;

@Controller
public class UserController {

	
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
	@GetMapping("/users/required_loginform")
	public String required_loginform() {
		
		return "users/required_loginform";
	}
	
	@PostMapping("/users/login_fail")
	public String loginFail() {
		

		return "users/login_fail";
	}
	
	@PostMapping("/users/login_success")
	public String loginSuccess(LoginRequest dto, HttpServletRequest request) throws Exception {
	
		return "users/login_success";
	}
	
}












