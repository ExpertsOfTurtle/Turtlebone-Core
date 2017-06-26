package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class OptionGroupModel implements Serializable{
	
	private Integer groupid;
	private String groupname;
	private Integer type;
		
}