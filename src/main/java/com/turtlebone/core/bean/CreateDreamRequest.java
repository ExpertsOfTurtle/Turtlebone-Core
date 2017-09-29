package com.turtlebone.core.bean;

import lombok.Data;

@Data
public class CreateDreamRequest {
	private String username;
	private String content;
	private String date;
}
