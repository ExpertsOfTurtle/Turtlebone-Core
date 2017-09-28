package com.turtlebone.core.builder.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.turtlebone.core.controller.ChooseController;
import com.turtlebone.core.enums.ActivityType;
import com.turtlebone.core.enums.sudoku.SudokuLevel;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.util.DateUtil;
import com.turtlebone.core.util.StringUtil;

@Component
public class DreamActivityBuilder {
	private static Logger logger = LoggerFactory.getLogger(DreamActivityBuilder.class);
	
	public ActivityModel build(String username, String datetime, String content) {
		logger.debug("[Dream]username={},datetime={},content={}", username, datetime, content);
		ActivityModel model = new ActivityModel();
		model.setUsername(username);
		if (StringUtil.isEmpty(datetime)) {
			model.setDatetime(DateUtil.getDateTime());
		} else {
			model.setDatetime(datetime);
		}
		model.setType(ActivityType.DREAM.name());
		model.setResult(content);
		
		String ct = content.length() > 30 ? content.substring(0, 30) + "..." : content;
		String description = String.format("[%s] [发梦]了，内容：[%s]", 
				username, ct);
		model.setDescription(description);
		logger.info(description);
		return model;
	}
}
