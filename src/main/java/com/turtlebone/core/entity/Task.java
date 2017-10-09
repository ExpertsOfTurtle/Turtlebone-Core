package com.turtlebone.core.entity;

import lombok.Data;

@Data
public class Task{
	
	private Integer id;
	private String creator;
	private String owner;
	private String deadline;
	private Integer punishmentId;
	private Integer status;
	private String content;
			
}
















