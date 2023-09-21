package com.example.boot09.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users") // table 명을 명시하지 않으면 클래스명과 동일하게 만들어진다
public class User {
	@Id
	private int id;
	//사용자명(user id) 은 중복된 데이터가 들어가지 않도록
	@Column(unique = true)
	private String userName;
	private String password;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다. 
	private String role;
}
