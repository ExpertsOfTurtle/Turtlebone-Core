
package com.turtlebone.core.service;

import java.util.List;

import com.turtlebone.core.entity.OptionGroup;
import com.turtlebone.core.model.OptionGroupModel;

public interface OptionGroupService{
	
	public int create(OptionGroupModel optionGroupModel);
	
	public int createSelective(OptionGroupModel optionGroupModel);
	
	public OptionGroupModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(OptionGroupModel optionGroupModel);
	
	public int updateByPrimaryKeySelective(OptionGroupModel optionGroupModel);
	
	public int deleteByPrimaryKey(Integer id);
	
	public OptionGroupModel selectByName(String groupname);	

	public int selectCount(OptionGroupModel optionGroupModel);
	
	public List<OptionGroupModel> selectAll();
}