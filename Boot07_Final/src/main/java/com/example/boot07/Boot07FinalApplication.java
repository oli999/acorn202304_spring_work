package com.example.boot07;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot07FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot07FinalApplication.class, args);
		
		// 크롬을 실행해서 http://localhost:9000/boot07 로딩하기 
		Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("cmd /c start chrome.exe http://localhost:9000/boot07");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
