package com.gura.spring02.guest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.guest.dto.GuestDto;

// component scan 
@Repository
public class GuestDaoImpl implements GuestDao{
	
	//mybatis 기반으로 DB 연동을 하기 위한 핵심 의존객체를 Dependency Inection 받는다.
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(GuestDto dto) {
		/*
		 *  Mapper's namespace => guest
		 *  sql's id => insert
		 *  parameterType => GuestDto
		 */
		session.insert("guest.insert", dto);
	}

	@Override
	public void update(GuestDto dto) {
		session.update("guest.update", dto);
	}

	@Override
	public void delete(GuestDto dto) {
		session.delete("guest.delete", dto);
	}

	@Override
	public GuestDto getData(int num) {
		/*
		 *  Mapper's namespace => guest
		 *  sql's id => getData
		 *  parameterType => int
		 *  resultType => GuestDto
		 */
		return session.selectOne("guest.getData", num);
	}

	@Override
	public List<GuestDto> getList() {
		/*
		 *  Mapper's namespace => guest
		 *  sql's id => getList
		 *  parameterType => X
		 *  resultType => GuestDto
		 */
		return session.selectList("guest.getList");
	}

}











