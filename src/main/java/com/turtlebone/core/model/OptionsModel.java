package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class OptionsModel implements Serializable{
	
	private Integer optionid;
	private Integer groupid;
	private String optionname;
	private Integer probability;
		
		
}