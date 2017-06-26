package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class ActivityModel implements Serializable{
	
	private Integer idactivity;
	private String username;
	private String datetime;
	private String type;
	private String description;
	private Long result1;
	private Long result2;
	private Long result3;
	private String strresult1;
	private String strresult2;
	private String strresult3;
	private String result;
		
}