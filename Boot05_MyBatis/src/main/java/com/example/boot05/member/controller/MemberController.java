package com.example.boot05.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot05.member.dao.MemberDao;
import com.example.boot05.member.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired MemberDao dao;
	
	@GetMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		
		//회원 목록
		List<MemberDto> list=dao.getList();
		
		mView.addObject("list", list);
		mView.setViewName("member/list");
		
		return mView;
	}
}












