
package com.turtlebone.core.service;

import com.turtlebone.core.model.OptionsModel;

public interface OptionsService{
	
	public int create(OptionsModel optionsModel);
	
	public int createSelective(OptionsModel optionsModel);
	
	public OptionsModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(OptionsModel optionsModel);
	
	public int updateByPrimaryKeySelective(OptionsModel optionsModel);
	
	public int deleteByPrimaryKey(Integer id);
	

	public int selectCount(OptionsModel optionsModel);
	
}