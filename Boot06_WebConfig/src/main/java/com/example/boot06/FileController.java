package com.example.boot06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName,
			String saveFileName, long fileSize) throws UnsupportedEncodingException, FileNotFoundException{
		//원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.
		
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(orgFileName, "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		//파일의 크기 정보도 담아준다.
		headers.setContentLength(fileSize);
		
		//읽어들일 파일의 경로 구성
		String filePath=fileLocation + File.separator + saveFileName;
		//파일에서 읽어들일 스트림 객체
		InputStream is=new FileInputStream(filePath);
		//InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr=new InputStreamResource(is);
		
		//ResponseEntity 객체의 참조값 얻어내기 
		ResponseEntity<InputStreamResource> resEn=ResponseEntity.ok()
			.headers(headers)
			.body(isr);
			
		return resEn;
	}
	
	@GetMapping("/file/uploadform")
	public String uploadform() {
		return "file/uploadform";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto, Model model) {
		/* 
		 * 	dto 에 있는 정보를 이용해서  C:/acorn202304/upload 폴더에 업로된 파일을 저장해 보세요.
		 * 	
		 *  저장되는 파일명은  UUID 로 만들어 주세요
		 */
		//업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값 얻어오기 
		MultipartFile myFile=dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//저장할 파일명 구성하기
		String saveFileName=UUID.randomUUID().toString();
		//파일경로 구성
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//원하는 경로에 파일 저장하기 
			myFile.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//원래는 DB 에 저장해야 하지만 테스트를 위해  view page 에 전달하기 
		
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("saveFileName", saveFileName);
		model.addAttribute("fileSize", fileSize);
		
		return "file/upload";
	}
}









