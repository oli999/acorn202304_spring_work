package com.example.boot05.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot05.member.dto.MemberDto;

@Repository
public class MemberDao {
	
	@Autowired SqlSession session;
	
	public List<MemberDto> getList(){
		return session.selectList("member.getList");
	}	
}










