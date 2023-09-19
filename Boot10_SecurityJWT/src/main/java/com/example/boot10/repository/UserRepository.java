package com.example.boot10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot10.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUserName(String userName);
}
