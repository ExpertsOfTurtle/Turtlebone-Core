
package com.turtlebone.core.service;

import com.turtlebone.core.model.PunishmentModel;

public interface PunishmentService{
	
	public int create(PunishmentModel punishmentModel);
	
	public int createSelective(PunishmentModel punishmentModel);
	
	public PunishmentModel findByPrimaryKey(Integer id);
	
	public int updateByPrimaryKey(PunishmentModel punishmentModel);
	
	public int updateByPrimaryKeySelective(PunishmentModel punishmentModel);
	
	public int deleteByPrimaryKey(Integer id);
	

	public int selectCount(PunishmentModel punishmentModel);
	
}