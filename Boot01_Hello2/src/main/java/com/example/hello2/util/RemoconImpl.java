package com.example.hello2.util;

public class RemoconImpl implements Remocon{

	@Override
	public void up() {
		System.out.println("볼륨을 올려요");
	}

	@Override
	public void down() {
		System.err.println("볼륨을 내려요");
	}

}
