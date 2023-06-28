package com.gura.spring04.file.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.file.dao.FileDao;
import com.gura.spring04.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileDao dao;

	@Override
	public void getList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFileData(int num, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
