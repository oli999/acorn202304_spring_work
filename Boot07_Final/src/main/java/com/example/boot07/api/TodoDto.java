package com.example.boot07.api;

public class TodoDto {
	private int id;
	private String title;
	private boolean complete;
	
	public TodoDto() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public void showInfo() {
		System.out.println(id+","+title+","+complete);
	}
	
}
