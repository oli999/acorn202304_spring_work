package com.example.boot09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String loginSuccess(){
	
		return "users/login_success";
	}
	
	@GetMapping("/users/denied")
	public String denied() {
		return "users/denied";
	}
	
}












