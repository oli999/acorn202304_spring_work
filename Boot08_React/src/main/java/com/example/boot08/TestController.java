package com.example.boot08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot08.util.JwtUtil;

@RestController
public class TestController {
	@Autowired
	private JwtUtil util;
	/*
	 *  test 로 발급되는 토큰을 https://jwt.io 에서 디코딩 해보기 
	 */
	@GetMapping("/hello/token")
	public String helloToken() {
		
		String token=util.generateToken("kimgura");
		return token;
	}
	
	@GetMapping("/fortune")
	public String fortune() {
		
		return "lucky!";
	}
	
	@GetMapping("/auth")
	public String auth() {
		
		return "auth ok!";
	}
	
}






















