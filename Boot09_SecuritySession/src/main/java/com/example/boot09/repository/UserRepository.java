package com.example.boot09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot09.entity.User;
/*
 *  JPA repository 만들기
 *  
 *  extends JpaRepository< Entity 클래스, Entity 클래스 안에서 id 역활을 하는 필드의 type> 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);
}
