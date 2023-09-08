package com.example.boot08.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot08.util.JwtUtil;

@RestController
public class UsersController {
	
	@Autowired 
	private JwtUtil jwtUtil;
	//SecurityConfig 에서 Bean 으로 등록한 객체 
	@Autowired
	private AuthenticationManager authManager;
	
	// userName, password 를 전달 받아서 유효한 정보이면 토큰을 응답하는 컨트롤러 메소드 (로그인 메소드)
	@PostMapping("/auth")
	public String auth(@RequestBody Users users) throws Exception {
		System.out.println(users.getUserName()+"|"+users.getPassword());
		try {
			authManager.authenticate
			(new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));
		}catch(Exception e) {
			//예외가 발생하면 아이디 혹은 비밀번호가 틀린것이다.
			e.printStackTrace();
			throw new Exception("아이디 혹은 비밀번호가 틀려요!");
		}
		//여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(users.getUserName());
		return token;
	}
	
}














