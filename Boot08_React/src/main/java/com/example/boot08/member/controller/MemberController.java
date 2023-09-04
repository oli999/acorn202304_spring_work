package com.example.boot08.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot08.member.entity.Member;
import com.example.boot08.member.repository.MemberRepository;



@RestController
public class MemberController {
	
	//DAO 라고 생각하면 된다.
	@Autowired MemberRepository repo;
	
	@GetMapping("/members")
	public List<Member> getList(){
		
		//return repo.findAll();
		return repo.findAllByOrderByNumDesc();
	}
	
	@PostMapping("/members")
	public Member insert(@RequestBody Member mem) {
		//MemberRepository 객체를 이용해서 저장하고 결과를 리턴받는다.
		Member result=repo.save(mem);
		//추가된 회원정보를 json 으로 응답
		return result;
	}
	
	@DeleteMapping("/members/{num}")
	public Map<String, Object> delete(@PathVariable("num") int num) {
		repo.deleteById(num);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
	@GetMapping("/members/{num}")
	public Member getData(@PathVariable("num") int num) {
		// Optional 객체에서 Member type 객체 얻어내기 
		return repo.findById(num).get();
	}
	
	@PutMapping("/members/{num}")
	public Member update(@RequestBody Member mem) {
		Member result=repo.save(mem);
		return result;
	}
}
























