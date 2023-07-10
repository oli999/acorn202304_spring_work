package com.example.hello2.pc;

public class Computer {
	private Cpu cpu;
	
	//생성자
	public Computer(Cpu cpu) {
		this.cpu=cpu;
	}
	//메소드
	public void action() {
		System.out.println("Computer 가 동작합니다");
	}
}
