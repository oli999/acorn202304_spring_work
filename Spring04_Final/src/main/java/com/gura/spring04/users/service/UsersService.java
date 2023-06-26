package com.gura.spring04.users.service;

import javax.servlet.http.HttpSession;

import com.gura.spring04.users.dto.UsersDto;

public interface UsersService {
	public void addUser(UsersDto dto);
	public void loginProcess(UsersDto dto, HttpSession session);
}
