package com.example.boot06;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/*
 *  @PropertySource(value=" 커스텀 properties 파일의 위치" )
 *  classpath: 는 resources 폴더를 가리킨다.
 */
@SpringBootApplication
@PropertySource(value = "classpath:custom.properties")
public class Boot06WebConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot06WebConfigApplication.class, args);
		
		// 크롬을 실행해서 http://localhost:9000/boot05 로딩하기 
		Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("cmd /c start chrome.exe http://localhost:9000/boot06");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
