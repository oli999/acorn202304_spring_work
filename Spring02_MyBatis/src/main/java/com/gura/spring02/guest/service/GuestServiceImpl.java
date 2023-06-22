package com.gura.spring02.guest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.guest.dao.GuestDao;
import com.gura.spring02.guest.dto.GuestDto;

// component scan 을 통해서 bean 이 될수 있도록 서비스 클래스는 @Service 어노테이션을 붙인다.
@Service
public class GuestServiceImpl implements GuestService{
	//서비스는 비즈니스 로직을 처리하기 위해 Dao 에 의존한다.
	@Autowired
	private GuestDao dao;
	//방명록 하나를 추가하는 로직 처리를 하는 메소드 
	@Override
	public void addGuest(GuestDto dto) {
		dao.insert(dto);
	}
	//방명록 하나를 수정하는 로직 처리를 하는 메소드 
	@Override
	public void updateGuest(GuestDto dto) {
		dao.update(dto);
	}
	//방명록 하나를 삭제하는 로직 처리를 하는 메소드 
	@Override
	public void deleteGuest(GuestDto dto) {
		dao.delete(dto);
	}
	//메소드의 인자로 전달된 ModelAndView 객체에 글하나의 정보를 담는 로직을 처리하는 메소드 
	@Override
	public void getGuestInfo(ModelAndView mView, int num) {
		GuestDto dto=dao.getData(num);
		mView.addObject("dto", dto);
	}
	//메소드의 인자로 전달된 ModelAndView 객체에 글목록을 담는 로직을 처리하는 메소드 
	@Override
	public void getGuestList(ModelAndView mView) {
		List<GuestDto> list=dao.getList();
		mView.addObject("list", list);
	}

}



