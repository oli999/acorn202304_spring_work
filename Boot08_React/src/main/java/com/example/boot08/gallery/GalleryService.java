package com.example.boot08.gallery;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GalleryService {
	public void saveGallery(Gallery gallery);
	public List<Gallery> getList();
	public Page<Gallery> getList(int page);
}
