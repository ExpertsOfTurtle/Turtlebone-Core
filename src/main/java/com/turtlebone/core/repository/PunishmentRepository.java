package com.turtlebone.core.repository;

import com.turtlebone.core.entity.Punishment;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishmentRepository{

  	int deleteByPrimaryKey(Integer id);
	
	Punishment selectByPrimaryKey(Integer id);
	
	    
    int updateByPrimaryKey(Punishment punishment);

    int updateByPrimaryKeySelective(Punishment punishment);

  	int insert(Punishment punishment);
  	
	int insertSelective(Punishment punishment);


    int selectCount(Punishment punishment);

    List<Punishment> selectPage(@Param("punishment") Punishment punishment, @Param("pageable") Pageable pageable);
	
}