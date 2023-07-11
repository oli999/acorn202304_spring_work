package com.example.aop.util;
/*
 *  MemberService 메소드의 getMember() 메소드에 적용할 Aspect 클래스를 만들어서
 *  getMember() 가 리턴해주는 MemberDto 객체에 1, "김구라", "노량진" 을 담아서 
 *  리턴되도록 프로그래밍 해 보세요.
 */
public class MemberService {
	
	public MemberDto getMember(int num) {
		//객체 생성을 해서 바로 리턴해주기  
		MemberDto dto=new MemberDto();
		return dto;
	}
}
