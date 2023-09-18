package com.example.boot09.entity;

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
	private String userName;
	private String password;
	private String email;
	private String role;
}
