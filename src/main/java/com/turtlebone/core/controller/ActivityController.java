package com.turtlebone.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.turtlebone.core.bean.DreamActivityRequest;
import com.turtlebone.core.bean.QueryActivityRequest;
import com.turtlebone.core.bean.SudokuActivity;
import com.turtlebone.core.builder.activity.DreamActivityBuilder;
import com.turtlebone.core.builder.activity.SudokuActivityBuilder;
import com.turtlebone.core.enums.settlement.service.CFSettlementService;
import com.turtlebone.core.enums.sudoku.SudokuLevel;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.model.UserModel;
import com.turtlebone.core.service.ActivityService;
import com.turtlebone.core.service.UserService;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "activity")
public class ActivityController {
	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	@Autowired
	private SudokuActivityBuilder sudokuActivityBuilder;
	@Autowired
	private DreamActivityBuilder dreamActivityBuilder;

	@RequestMapping(value = "/query")
	public @ResponseBody ResponseEntity<?> queryActivity(@RequestBody QueryActivityRequest request) {
		logger.debug("request:{}", JSON.toJSONString(request));
		List<ActivityModel> list = activityService.selectByCondition(request.getUsername(), request.getType(),
				request.getPageSize(), request.getOffset());
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value = "/detail/{id}")
	public String queryDetail(Map<String, Object> model,  @PathVariable("id") Integer id) {
		ActivityModel detail = activityService.findByPrimaryKey(id);
		model.put("detail", detail);
		return "activity/ajax/detail";
	}
	
	@RequestMapping(value = "/sudoku")
	public @ResponseBody ResponseEntity<?> addSudokuActivity(@RequestBody SudokuActivity sudokuActivity) {
		logger.debug("request:{}", JSON.toJSONString(sudokuActivity));
		String username = sudokuActivity.getUsername();
		String datetime = sudokuActivity.getDatetime();
		Integer usetime = sudokuActivity.getUsetime();
		SudokuLevel level = sudokuActivity.getLevel();
		Integer gameId = sudokuActivity.getGameId();
		Integer rank = sudokuActivity.getRank();
		Integer problemId = sudokuActivity.getProblemId();
		ActivityModel activity = sudokuActivityBuilder.build(username, datetime, usetime, level, gameId, rank,
				problemId);
		activityService.create(activity);
		return ResponseEntity.ok(activity);
	}

}
