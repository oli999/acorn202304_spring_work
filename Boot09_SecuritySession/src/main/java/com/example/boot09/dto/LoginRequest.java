package com.example.boot09.dto;

import lombok.Data;

//로그인시 아이디와 비밀번호를 전달받을 객체
@Data
public class LoginRequest {
	private String userName;
	private String password;
}
