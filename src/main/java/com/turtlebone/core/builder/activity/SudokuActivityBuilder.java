package com.turtlebone.core.builder.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.turtlebone.core.controller.ChooseController;
import com.turtlebone.core.enums.ActivityType;
import com.turtlebone.core.enums.sudoku.SudokuLevel;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.util.DateUtil;
import com.turtlebone.core.util.StringUtil;

public class SudokuActivityBuilder {
	private static Logger logger = LoggerFactory.getLogger(SudokuActivityBuilder.class);
	
	public ActivityModel build(String username, String datetime, Integer usetime,
			SudokuLevel level, Integer gameId, Integer rank, Integer problemId) {
		logger.debug("[SUDOKU]username={},usetime={},level={}", username, usetime, level.getDescription());
		ActivityModel model = new ActivityModel();
		model.setUsername(username);
		if (StringUtil.isEmpty(datetime)) {
			model.setDatetime(DateUtil.getDateTime());
		} else {
			model.setDatetime(datetime);
		}
		model.setType(ActivityType.SUDOKU.name());
		model.setResult1((long)usetime);
		model.setResult2((long)rank);
		model.setResult3((long)problemId);
		model.setStrresult1(level.getDescription());
		model.setStrresult2(gameId.toString());
		
		String description = String.format("[%s]进行[%s]数独,用时%d'%d'',排名%d", 
				username, level.getDescription(), usetime/60, usetime%60, rank);
		model.setDescription(description);
		logger.info(description);
		return model;
	}
}
