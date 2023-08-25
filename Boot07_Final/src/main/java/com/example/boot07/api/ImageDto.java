package com.example.boot07.api;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
	private String title;
	private MultipartFile image;
	
	public ImageDto() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	
}
