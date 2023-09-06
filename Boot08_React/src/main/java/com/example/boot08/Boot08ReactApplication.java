package com.example.boot08;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.boot08.member.entity.Member;
import com.example.boot08.member.repository.MemberRepository;
import com.example.boot08.users.Users;
import com.example.boot08.users.UsersRepository;

@SpringBootApplication
public class Boot08ReactApplication {
	
	@Autowired
	private MemberRepository repo;
	@Autowired
	private UsersRepository usersRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	//Boot08ReactApplication 객체가 생성된 이후에 자동 호출될 메소드 
	@PostConstruct
	public void initMembers() {
		//DB 에 Sample 데이터를 저장하기 
		List<Member> list=new ArrayList<Member>();
		list.add(new Member(0, "김구라", "노량진"));
		list.add(new Member(0, "해골", "행신동"));
		list.add(new Member(0, "원숭이", "동물원"));
		
		repo.saveAll(list);
		
		//DB Sample Users 담기
		Users user1=new Users();
		user1.setUserName("kimgura");
		user1.setPassword(encoder.encode("1234"));
		user1.setEmail("naver@");
		
		usersRepo.save(user1);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Boot08ReactApplication.class, args);
	}

}








