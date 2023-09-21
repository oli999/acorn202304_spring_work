package com.example.boot09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	//자발적으로 로그인 링크를 눌러서 로그인 하는 폼 
	@GetMapping("/users/loginform")
	public String loginform() {
		
		return "users/loginform";
	}
	//로그인 하지 않은 상태로 인증이 필요한 경로를 요청하게 되면 자동으로 리다일렉트 이동되는 폼 
	@GetMapping("/users/required_loginform")
	public String required_loginform() {
	
		return "users/required_loginform";
	}
	// Post 방식으로 폼을 제출해서 로그인 요청을 했지만 아이디 혹은 비밀번호가 다른경우 forward 이동되는 요청 처리   
	@PostMapping("/users/login_fail")
	public String loginFail() {
		
		return "users/login_fail";
	}
	// Post 방식으로 폼을 제출해서 로그인 요청을 해서 성공인 경우 forward 이동되는 요청 처리 (자발적 로그인인 경우)   
	@PostMapping("/users/login_success")
	public String loginSuccess(){
	
		return "users/login_success";
	}
	// 권한이 부족한 경로로 요청을 했을때 forward 이동되는 요청 처리 
	@GetMapping("/users/denied")
	public String denied() {
		return "users/denied";
	}
	// session 이 강제로 종료 되었을경우 리다일렉트 되는 경로 
	@GetMapping("/users/expired")
	public String expired() {
		return "users/expired";
	}
	
}












