
package com.turtlebone.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.turtlebone.core.entity.Punishment;
import com.turtlebone.core.repository.PunishmentRepository;
import com.turtlebone.core.model.PunishmentModel;
import com.turtlebone.core.service.PunishmentService;
import com.turtlebone.core.util.BeanCopyUtils;

@Service
public class PunishmentServiceImpl implements PunishmentService {
	@Autowired
	private PunishmentRepository punishmentRepo;
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return punishmentRepo.deleteByPrimaryKey(id);
	}
	

    /*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public PunishmentModel findByPrimaryKey(Integer id) {
		Punishment punishment = punishmentRepo.selectByPrimaryKey(id);
		return BeanCopyUtils.map(punishment, PunishmentModel.class);
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKey(PunishmentModel punishmentModel) {
		return punishmentRepo.updateByPrimaryKey(BeanCopyUtils.map(punishmentModel, Punishment.class));
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKeySelective(PunishmentModel punishmentModel) {
		return punishmentRepo.updateByPrimaryKeySelective(BeanCopyUtils.map(punishmentModel, Punishment.class));
	}
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int create(PunishmentModel punishmentModel) {
		return punishmentRepo.insert(BeanCopyUtils.map(punishmentModel, Punishment.class));
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int createSelective(PunishmentModel punishmentModel) {
		return punishmentRepo.insertSelective(BeanCopyUtils.map(punishmentModel, Punishment.class));
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int selectCount(PunishmentModel punishmentModel) {
		return punishmentRepo.selectCount(BeanCopyUtils.map(punishmentModel, Punishment.class));
	}



}
