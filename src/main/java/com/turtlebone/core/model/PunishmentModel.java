package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class PunishmentModel implements Serializable{
	
	private Integer id;
	private Integer type;
	private Integer val;
			
}