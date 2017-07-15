
package com.turtlebone.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.turtlebone.core.entity.Activity;
import com.turtlebone.core.repository.ActivityRepository;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.service.ActivityService;
import com.turtlebone.core.util.BeanCopyUtils;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return activityRepo.deleteByPrimaryKey(id);
	}
	

    /*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public ActivityModel findByPrimaryKey(Integer id) {
		Activity activity = activityRepo.selectByPrimaryKey(id);
		return BeanCopyUtils.map(activity, ActivityModel.class);
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKey(ActivityModel activityModel) {
		return activityRepo.updateByPrimaryKey(BeanCopyUtils.map(activityModel, Activity.class));
	}
	
	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int updateByPrimaryKeySelective(ActivityModel activityModel) {
		return activityRepo.updateByPrimaryKeySelective(BeanCopyUtils.map(activityModel, Activity.class));
	}
	

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int create(ActivityModel activityModel) {
		Activity activity = BeanCopyUtils.map(activityModel, Activity.class);
		activityRepo.insert(activity);
		activityModel.setIdactivity(activity.getIdactivity());
		return activity.getIdactivity();
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int createSelective(ActivityModel activityModel) {
		return activityRepo.insertSelective(BeanCopyUtils.map(activityModel, Activity.class));
	}

	/*
	 * @Transactional is not necessarry for the single atomic CRUD statement for better performance, 
	 * but you still have to take care of @Transactional for multi-statements scenario.
	 * if read only,please config as "@Transactional(readOnly = true)",otherwise "@Transactional"
	 */
	@Override
	public int selectCount(ActivityModel activityModel) {
		return activityRepo.selectCount(BeanCopyUtils.map(activityModel, Activity.class));
	}


	@Override
	public List<ActivityModel> selectByCondition(String username, String type) {
		Map<String, String> map = new HashMap<>();
		if (username != null) {
			map.put("username", username);
		}
		if (type != null) {
			map.put("type", type);
		}
		List<Activity> list = activityRepo.selectByCondition(map);
		return BeanCopyUtils.mapList(list, ActivityModel.class);
	}

}
