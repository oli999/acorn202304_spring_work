package com.gura.spring03.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring03.file.dto.FileDto;
/*
 *   [ spring mvc 파일 업로드 처리 ]
 *   
 *   1. pom.xml 에  commons-io, commons-fileupload 가 dependency 에 명시 되어 있어야 한다.
 *   2. servlet-context.xml 에  MultipartResolver bean 설정이 있어야한다.
 *   3. MultipartFile 객체를 컨트롤러에서 받아서 사용하면 된다.
 *       
 */
@Controller
public class FileController {	
	//이미지 업로드 요청처리 
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, value="/image/upload")
	public Map<String, Object> imageUpload(MultipartFile image, HttpServletRequest request) {
		//원본 파일명
		String orgFileName=image.getOriginalFilename();
		
		// webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/resources/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
			image.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Map 객체에 
		Map<String, Object> map=new HashMap<>();
		// "imagePath" 라는 키값으로 업로드된 이미지를 요청할수 있는 경로를 담아서 
		map.put("imagePath", "/resources/upload/"+saveFileName);
		// 리턴해주면   {"imagePath":"xxx"} 형식의 json 문자열이 응답된다.
		return map;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/file/upload2")
	public String upload(FileDto dto, HttpServletRequest request) { // FileDto 의 필드에  MultipartFile 객체의 참조값이 들어있다.
		//FileDto 객체에 들어있는 MultipartFile 객체를 이용해서 파일업로드 관련 처리를 한다.
		MultipartFile myFile=dto.getMyFile();
		//1. 원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//2. 파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/resources/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "file/upload";
	}
	
	/*
	 *  [ 파일 업로드 요청 처리 ] 
	 *  title 이라는 파라미터 명으로 제목(파일의 설명)
	 *  myFile 이라는 파라미터 명으로 첨부파일이 전송된다.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/file/upload")
	public String upload(String title, MultipartFile myFile, HttpServletRequest request) {
		/*
		 *  입력한 제목은 title 에 들어 있고, 업로드된 파일에 대한 정보는 myFile 객체에 들어 있다.
		 *  따라서 myFile 객체의 메소드를 활용해서 업로드 처리에 필요한 정보를 얻어내고 어떤 동작을 수행하면 된다. 
		 *  참고로 일반적은 웹서버 application 은 클라이언트가 파일 업로드를 해 오면 운영체제의 임시폴더에
		 *  이상한(긴) 파일명으로 일단 저장을 해 놓는다. 
		 *  일정시간 이후에 임시 폴더에 저장되었던 파일은 운영체제가 자동으로 지우거나 비운다.
		 *  따라서 해당 파일을 사용하기 위해서는 원하는 위치로 이동(copy) 시켜야 한다. 
		 */
		//1. 원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//2. 파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/resources/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/resources/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "file/upload";
	}
	
	@RequestMapping("/file/insertform")
	public String insertform() {
		// /WEB-INF/views/file/insertform.jsp 페이지로 forward 이동해서 응답
		return "file/insertform";
	}	
}





