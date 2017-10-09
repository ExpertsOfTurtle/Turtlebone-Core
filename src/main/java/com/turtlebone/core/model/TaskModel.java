package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class TaskModel implements Serializable{
	
	private Integer id;
	private String creator;
	private String owner;
	private String deadline;
	private Integer punishmentId;
	private Integer status;
	private String content;
		
}