package com.example.boot03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody 가 기본인 컨트롤러
@RestController
public class MemberController {
	
	// post 방식 /member/insert 요청 처리 
	@PostMapping("/member/insert")
	public Map<String, Object> insert(String name, String addr) {
		//전송된 파라미터 출력 
		System.out.println(name+" | "+addr);
		//json 문자열을 응답하기 위해 Map 에 정보를 담아서 
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		//Map 을 리턴하면 json 문자열이 응답된다.
		return map;
	}
}










