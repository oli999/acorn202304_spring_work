package com.example.boot06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
	
	//application.properties 파일에 있는 정보 얻어내기
	@Value("${file.location}")
	private String fileLocation;
	
	// jpg, png, gif 이미지 데이터를 응답할수 있도록 produces 에 배열로 전달한다.
	@GetMapping(
		value = "/aaa/{imageName}",
		produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
				MediaType.IMAGE_GIF_VALUE }
	)
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.
		
		// 읽어들일 파일의 경로
		// C:/acorn202304/upload/kim1.png  형식의 경로 
		String absolutePath=fileLocation + File.separator + imageName;
		// 파일에서 읽어들일 InputStream 
		InputStream is=new FileInputStream(absolutePath);
		// fileLocation 필드에는 파일이 저장되어 있는 서버의 파일 시스템상에서의 위치가 들어 있다.
		return IOUtils.toByteArray(is);
	}
}














