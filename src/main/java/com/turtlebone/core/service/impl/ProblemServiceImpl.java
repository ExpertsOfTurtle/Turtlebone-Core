
package com.turtlebone.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.turtlebone.core.entity.Problem;
import com.turtlebone.core.repository.ProblemRepository;
import com.turtlebone.core.model.ProblemModel;
import com.turtlebone.core.service.ProblemService;
import com.turtlebone.core.util.BeanCopyUtils;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepo;
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		return problemRepo.deleteByPrimaryKey(id);
	}
	

    /*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public ProblemModel findByPrimaryKey(Long id) {
		Problem problem = problemRepo.selectByPrimaryKey(id);
		return BeanCopyUtils.map(problem, ProblemModel.class);
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKey(ProblemModel problemModel) {
		return problemRepo.updateByPrimaryKey(BeanCopyUtils.map(problemModel, Problem.class));
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKeySelective(ProblemModel problemModel) {
		return problemRepo.updateByPrimaryKeySelective(BeanCopyUtils.map(problemModel, Problem.class));
	}
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int create(ProblemModel problemModel) {
		return problemRepo.insert(BeanCopyUtils.map(problemModel, Problem.class));
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int createSelective(ProblemModel problemModel) {
		return problemRepo.insertSelective(BeanCopyUtils.map(problemModel, Problem.class));
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int selectCount(ProblemModel problemModel) {
		return problemRepo.selectCount(BeanCopyUtils.map(problemModel, Problem.class));
	}



}
