package com.turtlebone.core.model;
import java.io.Serializable;

public class OptionGroupModel implements Serializable{
	
	private Integer groupid;
	private String groupname;
		
	public void setGroupid(Integer groupid){
		this.groupid = groupid;
	}
	
	public Integer getGroupid(){
		return this.groupid;
	}
		
	public void setGroupname(String groupname){
		this.groupname = groupname;
	}
	
	public String getGroupname(){
		return this.groupname;
	}
		
		
}