package com.example.boot08.gallery;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer>{
	//번호에 대해서 내림차순 정렬된 목록을 반환하는 Repository 메소드 만들기 
	public List<Gallery> findAllByOrderByNumDesc();
	//원하는 페이지 데이터만 반환하는 메소드
	public Page<Gallery> findAll(Pageable pageable);
}
