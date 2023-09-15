package com.example.boot09.repository.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.boot09.entity.User;
import com.example.boot09.repository.UserRepository;

/*
 *  Spring Security 에서 중요한 역활을 하는 서비스 만들기 
 *  
 *  - UserDetailsService 인터페이스를 구현해서 만든다
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//DB 에서 username 을 이용해서 사용자 정보를 얻어온다( 이름 ,비밀번호, 이메일 , 권한 등등... ) 
		User user=repo.findByUserName(username);
		
		UserDetails userDetails=new org.springframework.security.core.userdetails
				.User(user.getUserName(), user.getPassword(), new ArrayList<>());
		return userDetails;
	}
}














