package com.gura.spring04.users.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gura.spring04.users.dao.UsersDao;
import com.gura.spring04.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao dao;
	
	@Override
	public void addUser(UsersDto dto) {
		//비밀번호를 암호화해줄 객체를 생성
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//암호화된 비밀번호 얻어내서 
		String encodedPwd=encoder.encode(dto.getPwd());
		//UsersDto 객체에 다시 담고 
		dto.setPwd(encodedPwd);
		//UsersDao 객체를 이용해서 DB 에 저장하기 
		dao.insert(dto);
	}

	@Override
	public void loginProcess(UsersDto dto, HttpSession session) {
		//입력한 정보가 맞는지 여부
		boolean isValid=false;
		//아이디를 이용해서 회원 정보를 얻어온다.
		UsersDto resultDto=dao.getData(dto.getId());
		//만일 select 된 회원 정보가 존재하고 
		if(resultDto != null) {
			//Bcrypt 클래스의 static 메소드를 이용해서 입력한 비밀번호와 암호화 해서 저장된 비밀번호 일치 여부를 알아내야한다.
			isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
		}
		
		//만일 유효한 정보이면 
		if(isValid) {
			//로그인 처리를 한다.
			session.setAttribute("id", resultDto.getId());
		}		
	}

}























