package com.gura.spring02.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao dao;
	
	/*
	 *	 @RequestMapping 에  method 속성의 값을 명시 하지 않으면 요청 방식을 가리지 않고 
	 *   경로만 맞으면 모두 처리해준다 (GET, POST, PUT, DELETE)
	 *   
	 *   method 속성의 값을 명시하면 경로가 맞더라도 요청 방식이 다르면 처리해 주지 않는다.
	 *   일반적으로 POST 인 경우에는 요청 방식을 명시해 주는것이 좋다.
	 */
	
	//회원 수정 요청 처리
	@RequestMapping(method = RequestMethod.POST, value = "/member/update")
	public String update(MemberDto dto) {
		
		dao.update(dto);
		
		return "member/update";
	}
	
	//회원 수정폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(ModelAndView mView, int num) {
		//수정할 회원의 정보를 얻어온다.
		MemberDto dto=dao.getData(num);
		/*
		 * 수정할 회원의 정보를 ModelAndView 객체의 addObjct(key, value) 메소드를 이용해서 담는다.
		 * ModelAndView 객체에 담은 값은 결국 HttpServletRequest 객체에 담긴다( request scope 에 담긴다)
		 */
		mView.addObject("dto", dto);
		//view page 의 위치도 ModelAndView 객체에 담아서 리턴해야 한다.
		mView.setViewName("member/updateform");
		//모델(data) 와 view page 의 정보가 모두 담긴 ModelAndView 객체를 리턴해주면 
		//spring 이 알아서 처리해 준다.
		return mView;
	}
	
	/*
	 *  @RequestParam(value="파라미터명", defalutValue="기본값") 
	 *  기본값은 없어도 되고 파라미터명과 매개변수명이 일치한다면 생략 가능
	 */
	
	//회원 삭제 요청처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam(value = "num", defaultValue = "0") int num) {
		//MemberDao 객체를이용해서 삭제
		dao.delete(num);
		//목록보기로 리다일렉트 응답
		return "redirect:/member/list";
	}
	
	//회원추가 요청처리
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {
		//MemberDao 객체를 이용해서 DB 에 저장
		dao.insert(dto);
		// view page 로 forward 이동해서 응답
		return "member/insert";
	}
	
	//회원추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		// /WEB-INF/views/member/insertform.jsp 페이지로 forward 이동해서 응답
		return "member/insertform";
	}
	
	//회원 목록 보기 요청 처리 
	@RequestMapping("/member/list")
	public String list(HttpServletRequest request) {
		//회원목록을 얻어와서
		List<MemberDto> list=dao.getList();
		//request scope 에 담고 
		request.setAttribute("list", list);
		// /WEB-INF/views/member/list.jsp 페이지로 forward 이동해서 응답
		return "member/list";
	}
}








