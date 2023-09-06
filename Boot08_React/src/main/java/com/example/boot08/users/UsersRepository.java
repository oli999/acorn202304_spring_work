package com.example.boot08.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *    extends JpaRepository<Entity , id 역활을 하는 칼럼의 data type> 
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	//userName 으로 select 하는 메소드 추가 ( UserName 은  Entity 칼럼명과 같아야 한다 )
	public Users findByUserName(String userName);
}
