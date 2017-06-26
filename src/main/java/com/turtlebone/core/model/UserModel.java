package com.turtlebone.core.model;
import java.io.Serializable;

import com.turtlebone.core.entity.Problem;

import lombok.Data;
@Data
public class UserModel implements Serializable{
	
	private Integer id;
	private String loginName;
	private String loginPwd;
	private String createtime;
	private Float balance;
	private Integer usertype;
	private String nickname;
	
}