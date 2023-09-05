package com.example.boot08.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *    extends JpaRepository<Entity , id 역활을 하는 칼럼의 data type> 
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, String>{

}
