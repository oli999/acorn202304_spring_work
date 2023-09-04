package com.example.boot08.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity  // 이 클래스를 Entity 로 사용을 하겠다  => 이 클래스로 테이블을 만들겠다
@Table(name="MEMBERS") // 테이블을 만들때 테이블명은 MEMBER_TBL 로 하겠다 => 생략하면 클래스명으로 테이블이 만들어진다.
public class Member {
	
	@Id 
	// num 을 자동 증가되는 값을 얻어내서 사용하겠다는 의미 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int num; //해당 칼럼을 PK 로 지정
	private String name;
	private String addr;
	
}












