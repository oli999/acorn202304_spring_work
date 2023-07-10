package com.example.aop.util;

import org.springframework.stereotype.Component;

@Component
public class WritingUtil {
	//생성자
	public WritingUtil() {
		System.out.println("WritingUtil 생성자");
	}

	public void writeLetter() {
		
		System.out.println("편지를 써요");
		
	}
	public void writeReport() {
		
		System.out.println("보고서를 써요");
		
	}
	public void writeDiary() {
		
		System.out.println("일기를 써요");
		
	}
}



