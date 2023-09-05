package com.example.boot08.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Id
	private String userName;
	private String password;
	private String email;
}









