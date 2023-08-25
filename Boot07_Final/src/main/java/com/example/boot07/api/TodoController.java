package com.example.boot07.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	/*
	 *  application/json 형식의 요청 파라미터가 전달되면 @RequestBody 어노테이션을 이용해서 
	 *  요청 파라미터를 추출할수 있다. 
	 */
	@PostMapping("/todos")
	public Map<String, Object> insert(@RequestBody TodoDto dto){
		
		System.out.println(dto.getId()+"|"+dto.getTitle()+"|"+dto.isComplete());
		
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		
		
		return map;
	}
	
	@PostMapping("/todos2")
	public Map<String, Object> insert2(@RequestBody String json){
		
		System.out.println(json);
		/*
		 *  json 은  {"title":"xxx", "complete":false} 형식의 문자열 이다. 
		 */
		JSONObject jsonObj=new JSONObject(json);
		//title 추출
		String title=jsonObj.getString("title");
		//complete 추출
		boolean complete=jsonObj.getBoolean("complete");
		
		System.out.println(title+"|"+complete);
		
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		
		
		return map;
	}
	
}









