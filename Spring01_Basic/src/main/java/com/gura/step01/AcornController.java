package com.gura.step01;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AcornController {
	
	@RequestMapping("/person/today")
	public String showPerson(HttpServletRequest request) {
		String person="세종대왕";
		
		request.setAttribute("person", person);
		
		return "person";
	}
	
	@RequestMapping("/test/friends")
	public ModelAndView friends(ModelAndView mView) {
		
		List<String> names=new ArrayList<String>();
		mView.addObject("", names);
		mView.setViewName("");
		
		return mView;
	}
}









