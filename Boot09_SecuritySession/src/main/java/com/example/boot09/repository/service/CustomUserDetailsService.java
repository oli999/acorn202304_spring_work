package com.example.boot09.repository.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.boot09.entity.User;
import com.example.boot09.repository.UserRepository;

/*
 *  SecurityConfig 클래스에서 @Bean 설정으로 Bean 이 되는 AuthencticationManager 객체가 사용하는 서비스 객체이다.
 *  
 *  - UserDetailsService 인터페이스를 구현해서 만든다
 *  
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
	// DB 에서 사용자 정보를 얻어오기위한 repository
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//DB 에서 username 을 이용해서 사용자 정보를 얻어온다( 이름 ,비밀번호, 이메일 , 권한 등등... ) 
		User user=repo.findByUserName(username);

		/*
		 * UserDetails 객체에 부여된 권한 정보도 같이 담아 주어야 한다.
		 * 
		 * ROLE_XXX  => Authority
		 * XXX => ROLE
		 * 
		 * ROLE_ADMIN  =>  ROLE_ADMIN 이라는 Authority 를 가지고 있다 , ADMIN 이라는  ROLE 을 가지고 있다. 
		 * 
		 * 원래는 권한정보도 따로 Entity(테이블을) 만들어서 관리해서 하나의 계정이 다양한 권한을 가질수 있도록 해야 하지만
		 * 간단한 예제를 만들기 위해서 생략했다. 
		 */
		
		//권한은 1개 이지만 List 에 담아서 
		List<GrantedAuthority> authList=new ArrayList<>();
		authList.add(new SimpleGrantedAuthority(user.getRole()));
		
		//UserDetails 객체에 담아준다. 
		//Spring Security 가 제공하는 User 클래스는 UserDetails 인터페이스를 구현한 클래스 이다. 
		UserDetails userDetails=new org.springframework.security.core.userdetails
										.User(user.getUserName(), user.getPassword(), authList);

		return userDetails;
	}
}














