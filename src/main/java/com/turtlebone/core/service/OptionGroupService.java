
package com.turtlebone.core.service;

import com.turtlebone.core.model.OptionGroupModel;

public interface OptionGroupService{
	
	public int create(OptionGroupModel optionGroupModel);
	
	public int createSelective(OptionGroupModel optionGroupModel);
	
	public OptionGroupModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(OptionGroupModel optionGroupModel);
	
	public int updateByPrimaryKeySelective(OptionGroupModel optionGroupModel);
	
	public int deleteByPrimaryKey(Integer id);
	

	public int selectCount(OptionGroupModel optionGroupModel);
	
}