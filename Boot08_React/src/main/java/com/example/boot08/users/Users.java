package com.example.boot08.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "Users_tbl")
public class Users {
	
	//자동 부여되는 숫자를 Primary key 로 사용하겠다
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//사용자명(user id) 은 중복된 데이터가 들어가지 않도록
	@Column(unique = true)
	private String userName;
	//json 문자열을 Users 클래스를 이용해서 응답할때 비밀번호는 응답하지 않도록 배제 
	private String password;
	
	private String email;
	private String profile;
}




















