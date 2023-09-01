package com.example.boot08.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController  //REST API 를 좀더 편하게 만들기 위한 어노테이션 
public class NoticeController {
	//공지사항을 json 문자열  ["xxx", "xxx", .... ] 을 출력하기 위한 컨트롤러 메소드 
	@GetMapping("/notice")
	public List<String> getNotice(){
		//DB 에서 읽어온 공지 사항이라고 가정하자
		List<String> list=new ArrayList<String>();
		list.add("React 공부중입니다.");
		list.add("build 도 했는데 젬나네요!");
		list.add("어쩌구.. 저쩌구");
		return list;
	}
}









