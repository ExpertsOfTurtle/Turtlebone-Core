
package com.turtlebone.core.service;

import com.turtlebone.core.model.TaskModel;

public interface TaskService{
	
	public int create(TaskModel taskModel);
	
	public int createSelective(TaskModel taskModel);
	
	public TaskModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(TaskModel taskModel);
	
	public int updateByPrimaryKeySelective(TaskModel taskModel);
	
	public int deleteByPrimaryKey(Integer id);
	

	public int selectCount(TaskModel taskModel);
	
}