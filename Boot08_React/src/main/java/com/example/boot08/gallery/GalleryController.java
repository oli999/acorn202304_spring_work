package com.example.boot08.gallery;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GalleryController {
	//파일 저장경로
	@Value("${file.location}")
	private String fileLocation;	
	
	@Autowired
	private GalleryService service;
	
	// post 방식 파일 업로드 요청에 대해 처리할 컨트롤러 메소드
	@PostMapping("/gallery")
	public Map<String, Object> insert(Gallery gallery) {
		//SecurityContext 로 부터 업로드한 사용자(token 을 통해서 검증된) 정보를 얻어낸다.
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		gallery.setWriter(userName);
		service.saveGallery(gallery);
		
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		
		return map;
	}
//	
//	@GetMapping("/gallery")
//	public List<Gallery> getList(){
//		
//		return service.getList();
//	}
//	
	@GetMapping("/gallery")
	public Page<Gallery> getList(@RequestParam int pageNum){
		
		return service.getList(pageNum);
	}
	
	//이미지 데이터를 응답하는 컨트롤러 메소드
	@GetMapping(
			value="/gallery/images/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
						MediaType.IMAGE_GIF_VALUE}
	)
	public byte[] image(@PathVariable("imageName") String imageName) throws IOException {
		
		String absolutePath=fileLocation+File.separator+imageName;
		//파일에서 읽어들일 InputStream
		InputStream is=new FileInputStream(absolutePath);
		// 이미지 데이터(byte) 를 읽어서 배열에 담아서 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}	
}













