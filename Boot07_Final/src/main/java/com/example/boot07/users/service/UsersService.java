package com.example.boot07.users.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import com.example.boot07.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, HttpSession session);
	
	public void getInfo(HttpSession session, Model model);
	public void updateUserPwd(HttpSession session, UsersDto dto, Model model);
	public Map<String, Object> saveProfileImage(HttpServletRequest request, 
			MultipartFile mFile);
	public void updateUser(UsersDto dto, HttpSession session);
	public void deleteUser(HttpSession session, Model model);
}






