package com.example.boot07.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot07.cafe.dto.CafeCommentDto;



@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<CafeCommentDto> getList(CafeCommentDto dto) {
		
		return session.selectList("cafeComment.getList", dto);
	}

	@Override
	public void delete(int num) {
		session.update("cafeComment.delete", num);
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert", dto);
	}
	//저장될 예정인 댓글의 글번호를 얻어내서 리턴해주는 메소드 
	@Override
	public int getSequence() {
		return session.selectOne("cafeComment.getSequence");
	}

	@Override
	public void update(CafeCommentDto dto) {
		session.update("cafeComment.update", dto);
	}

	@Override
	public CafeCommentDto getData(int num) {
		
		return session.selectOne("cafeComment.getData", num);
	}
	//하나의 원글에 몇개의 댓글이 있는지 리턴하는 메소드 
	@Override
	public int getCount(int ref_group) {
		
		return session.selectOne("cafeComment.getCount", ref_group);
	}

}
