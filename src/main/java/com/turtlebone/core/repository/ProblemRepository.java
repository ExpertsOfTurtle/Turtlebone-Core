package com.turtlebone.core.repository;

import com.turtlebone.core.entity.Problem;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository{

  	int deleteByPrimaryKey(Long id);
	
	Problem selectByPrimaryKey(Long id);
	
	    
    int updateByPrimaryKey(Problem problem);

    int updateByPrimaryKeySelective(Problem problem);

  	int insert(Problem problem);
  	
	int insertSelective(Problem problem);


    int selectCount(Problem problem);

    List<Problem> selectPage(@Param("problem") Problem problem, @Param("pageable") Pageable pageable);
	
}