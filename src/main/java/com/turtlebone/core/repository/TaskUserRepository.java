package com.turtlebone.core.repository;

import com.turtlebone.core.entity.TaskUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUserRepository{

  	int deleteByPrimaryKey(Integer id);
	
	TaskUser selectByPrimaryKey(Integer id);
	
	    
    int updateByPrimaryKey(TaskUser taskUser);

    int updateByPrimaryKeySelective(TaskUser taskUser);

  	int insert(TaskUser taskUser);
  	
	int insertSelective(TaskUser taskUser);


    int selectCount(TaskUser taskUser);

    List<TaskUser> selectPage(@Param("taskUser") TaskUser taskUser, @Param("pageable") Pageable pageable);
	
}