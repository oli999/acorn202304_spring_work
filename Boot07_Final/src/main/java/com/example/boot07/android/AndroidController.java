package com.example.boot07.android;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AndroidController {	
	@GetMapping("/android/tweet")
	public String tweet(String msg) {
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		return "success!";
	}
}
