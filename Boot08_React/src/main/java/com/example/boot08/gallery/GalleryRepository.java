package com.example.boot08.gallery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer>{
	//번호에 대해서 내림차순 정렬된 목록을 반환하는 Repository 메소드 만들기 
	public List<Gallery> findAllByOrderByNumDesc();
}
