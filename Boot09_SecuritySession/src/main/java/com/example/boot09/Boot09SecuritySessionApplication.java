package com.example.boot09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.boot09.entity.User;
import com.example.boot09.repository.UserRepository;

@SpringBootApplication
public class Boot09SecuritySessionApplication {
	//JPA repository
	@Autowired
    private UserRepository repository;
	
	// Application 시작후 sample 사용자 저장하기 
    @PostConstruct
    public void initUsers() {
    	//비밀번호는 1234
    	String samplePwd=new BCryptPasswordEncoder().encode("1234");
        
    	// ADMIN, STAFF, USER 사용자 추가 
    	List<User> list=new ArrayList<User>();
    	list.add(new User(1, "kimgura", samplePwd, "aaa@naver.com", "ROLE_ADMIN"));
    	list.add(new User(2, "superman", samplePwd, "aaa2@naver.com", "ROLE_STAFF"));
    	list.add(new User(3, "batman", samplePwd, "aaa3@naver.com", "ROLE_USER"));
    	list.add(new User(4, "monkey", samplePwd, "aaa4@naver.com", "ROLE_USER"));
        repository.saveAll(list);
    }	

	public static void main(String[] args) {
		SpringApplication.run(Boot09SecuritySessionApplication.class, args);
	}

}






