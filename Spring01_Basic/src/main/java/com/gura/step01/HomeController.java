package com.gura.step01;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//이 프로젝트의 최상위 경로 요청이 오면 
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		//응답에 필요한 데이터(Model) 이라고 가정하자
		List<String> noticeList=new ArrayList<String>();
		noticeList.add("날씨가 많이 더워 지고 있어요");
		noticeList.add("어쩌구...");
		noticeList.add("저쩌구...");
		
		//home.jsp 페이지에서 필요한 모델(data) 를 HttpServletRequest 객체에 담아두기 
		request.setAttribute("noticeList", noticeList);
		
		// /WEB-INF/views/home.jsp 페이지로 forward 이동해서 응답하겠다는 의미
		//  "home" 이라는 문자열을 리턴하면  앞에 "/WEB-INF/views/" 뒤에 ".jsp" 가 자동으로 붙는다. 
		return "home";
	}
	
}

















