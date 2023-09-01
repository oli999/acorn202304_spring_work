package com.example.boot08.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot08.member.entity.Member;

/*
 *  Member Entity 에 대해서 insert, update, delete , select 작업을 대신해줄 
 *  Repository 만들기
 *  
 *  extends JpaRepository<Entity type, id 역활을 하는 필드의 type> 
 */
public interface MemberRepository extends JpaRepository<Member, Integer>{

}
