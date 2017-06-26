package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class ProblemModel implements Serializable{
	
	private Long id;
	private String problemNo;
	private String date;
	private String respondent;
	private String status;
	private String type;
	private String deadline;
		
}