package com.example.boot08.gallery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gallery {
	
	@Id 
	// num 을 자동 증가되는 값을 얻어내서 사용하겠다는 의미 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int num;
	private String writer;
	private String caption;
	private String imagePath;
	
	//테이블에 칼럼을 만들지 않겠다 
	@Transient 
	private MultipartFile image;
}












