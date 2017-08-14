
package com.turtlebone.core.service;

import java.util.List;
import java.util.Map;

import com.turtlebone.core.entity.Problem;
import com.turtlebone.core.model.ProblemModel;

public interface ProblemService{
	
	public int create(ProblemModel problemModel);
	
	public int createSelective(ProblemModel problemModel);
	
	public ProblemModel findByPrimaryKey(Long id);
	
	public int updateByPrimaryKey(ProblemModel problemModel);
	
	public int updateByPrimaryKeySelective(ProblemModel problemModel);
	
	public int deleteByPrimaryKey(Long id);
	

	public int selectCount(ProblemModel problemModel);
	
	public List<ProblemModel> selectByCondition(String username, String type, 
			String status, String deadlineFrom, String deadlineTo, String order);
	
	public ProblemModel selectIdOfNextProblem(String username, String type);
}