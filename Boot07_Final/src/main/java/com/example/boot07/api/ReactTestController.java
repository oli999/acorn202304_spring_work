package com.example.boot07.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.file.dto.FileDto;

@RestController
public class ReactTestController {
	
	@Autowired CafeDao cafeDao;
	
	@Value("${file.location}")
	private String fileLocation;
	
	/*
	 *  react js 에서
	 *  
	 *  const formData = new FormData();
	 *  formData.append("image", x );
	 *  
	 *  "image" 라는 키값으로 파일을 담았기때문에 
	 *  
	 *  MultipartFile image  와 같이 매개변수명을 일치 시켜야 한다. 
	 */
	@PostMapping("/api/image")
	public Map<String, Object> imageSave(MultipartFile image){
		String orgFileName=image.getOriginalFilename();
		//저장할 파일명  uuid 문자열 + 원본 파일명 
		String saveFileName=UUID.randomUUID().toString()+orgFileName;
		//db 에 저장할 저장할 파일의 상세 경로
		String filePath = fileLocation + File.separator + saveFileName;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(fileLocation);
		if(!upload.exists()) {
			//만약 디렉토리가 존재하지X
			upload.mkdir();//폴더 생성
		}
		try {
			//upload 폴더에 파일을 저장한다.
			image.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		
		return map;
	}
	
	
	@PostMapping("/api/image2")
	public Map<String, Object> imageSave2(ImageDto dto){
		//title 이 넘어오는지 확인 
		System.out.println("title:"+dto.getTitle());
		
		MultipartFile image=dto.getImage();
		String orgFileName=image.getOriginalFilename();
		//저장할 파일명  uuid 문자열 + 원본 파일명 
		String saveFileName=UUID.randomUUID().toString()+orgFileName;
		//db 에 저장할 저장할 파일의 상세 경로
		String filePath = fileLocation + File.separator + saveFileName;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(fileLocation);
		if(!upload.exists()) {
			//만약 디렉토리가 존재하지X
			upload.mkdir();//폴더 생성
		}
		try {
			//upload 폴더에 파일을 저장한다.
			image.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		
		return map;
	}
	
	//이미지 데이터를 응답하는 컨트롤러 메소드
	@GetMapping(
			value="/api/image/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
						MediaType.IMAGE_GIF_VALUE}
	)
	@ResponseBody
	public byte[] image(@PathVariable("imageName") String imageName) throws IOException {
		
		String absolutePath=fileLocation+File.separator+imageName;
		//파일에서 읽어들일 InputStream
		InputStream is=new FileInputStream(absolutePath);
		// 이미지 데이터(byte) 를 읽어서 배열에 담아서 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}	
	
	@GetMapping("/api/cafe")
	public List<CafeDto> cafeList(){
		//테스트로 글 10개만 가져오기 위해 
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(10);
		
		return cafeDao.getList(dto);
	}
	
	@PostMapping("/api/signup")
	public Map<String, Object> signup(@RequestBody String json){
		//가입할 아이디를 읽어온다.
		String id=new JSONObject(json).getString("id");
		boolean isSignupSuccess=false;
		
		// 입력한 아이디가 "kimgura" 가 아니면 가입 가능
		if(!id.equals("kimgura")) {
			isSignupSuccess=true;
		}else { // 그렇지 않은 경우에는 가입 불가 
			isSignupSuccess=false;
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSignupSuccess", isSignupSuccess);
		
		return map;
	}
	
	@PostMapping("/api/file")
	public Map<String, Object> upload(TestFileDto dto){
		MultipartFile myFile=dto.getMyFile();
		String orgFileName=myFile.getOriginalFilename();
		//저장할 파일명  uuid 문자열 + 원본 파일명 
		String saveFileName=UUID.randomUUID().toString()+orgFileName;
		//db 에 저장할 저장할 파일의 상세 경로
		String filePath = fileLocation + File.separator + saveFileName;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(fileLocation);
		if(!upload.exists()) {
			//만약 디렉토리가 존재하지X
			upload.mkdir();//폴더 생성
		}
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		long fileSize=myFile.getSize();
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		map.put("fileSize", fileSize);
		
		return map;
	}
	
	
	//다운로드해줄 파일의 번호가 요청 파라미터로 전달된다. 
	@GetMapping("/api/file")
	public ResponseEntity<InputStreamResource> download(TestFileDto dto) throws UnsupportedEncodingException, FileNotFoundException {
		
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		//파일의 크기 정보도 담아준다.
		headers.setContentLength(dto.getFileSize());
		
		//읽어들일 파일의 경로 구성
		String filePath=fileLocation + File.separator + dto.getSaveFileName();
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
		
}







