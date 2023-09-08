package com.example.boot08.gallery;

import java.util.List;

public interface GalleryService {
	public void saveGallery(Gallery gallery);
	public List<Gallery> getList();
}
