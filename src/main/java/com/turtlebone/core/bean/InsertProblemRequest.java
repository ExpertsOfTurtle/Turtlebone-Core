package com.turtlebone.core.bean;


import lombok.Data;

@Data
public class InsertProblemRequest {
	private String username;
	private String type;
	private String deadline;
	private Integer count;
}
