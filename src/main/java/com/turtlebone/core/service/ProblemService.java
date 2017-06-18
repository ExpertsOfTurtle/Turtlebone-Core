
package com.turtlebone.core.service;

import com.turtlebone.core.model.ProblemModel;

public interface ProblemService{
	
	public int create(ProblemModel problemModel);
	
	public int createSelective(ProblemModel problemModel);
	
	public ProblemModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ProblemModel problemModel);
	
	public int updateByPrimaryKeySelective(ProblemModel problemModel);
	
	public int deleteByPrimaryKey(Long id);
	

	public int selectCount(ProblemModel problemModel);
	
}