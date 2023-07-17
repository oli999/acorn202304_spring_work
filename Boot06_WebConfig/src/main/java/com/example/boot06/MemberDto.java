package com.example.boot06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //setter, getter 만들기
@Builder //setter 메소드 대신에 chain 형태로 값을 넣어줄수 있게 만들어준다
@NoArgsConstructor //디폴트 생성자
@AllArgsConstructor //인자로 모든 값이 전달되는 생성자 
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
