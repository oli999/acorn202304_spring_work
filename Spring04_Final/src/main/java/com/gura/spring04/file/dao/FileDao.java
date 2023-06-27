package com.gura.spring04.file.dao;

import java.util.List;

import com.gura.spring04.file.dto.FileDto;
//FileDaoImpl 클래스가 구현할 인터페이스 
public interface FileDao {
	public void insert(FileDto dto);
	public FileDto getData(int num);
	public void delete(int num);
	public List<FileDto> getList(FileDto dto);
	public int getCount(FileDto dto);
}
