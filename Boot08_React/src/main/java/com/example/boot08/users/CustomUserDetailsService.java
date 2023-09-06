package com.example.boot08.users;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*
 *   Spring Security 에서 제공해주는  UserDetailsService 구현 클래스를 정의한다.
 */
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsersRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username 에 해당되는 회원정보를 DB 에서 실제로 얻어내서
		Users users=repo.findByUserName(username); //비밀번호는 암호화 되어서 저장되어 있어야 한다. 
		//Spring Security User 객체를 생성해서 리턴해주면 된다.
		User user=new User(users.getUserName(), users.getPassword(), new ArrayList<>());
		//User 는  UserDetails 인터페이스를 구현한 클래스 이다. 
		return user;
	}

}









