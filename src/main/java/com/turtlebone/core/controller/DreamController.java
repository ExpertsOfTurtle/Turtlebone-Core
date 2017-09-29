package com.turtlebone.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.turtlebone.core.bean.ChooseInfo;
import com.turtlebone.core.bean.CreateDreamRequest;
import com.turtlebone.core.bean.DreamActivityRequest;
import com.turtlebone.core.builder.activity.DreamActivityBuilder;
import com.turtlebone.core.builder.activity.SudokuActivityBuilder;
import com.turtlebone.core.enums.ActivityType;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.model.UserModel;
import com.turtlebone.core.service.ActivityService;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;
import com.turtlebone.core.service.UserService;
import com.turtlebone.core.util.BeanCopyUtils;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/dream")
public class DreamController {
	private static Logger logger = LoggerFactory.getLogger(DreamController.class);

	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	@Autowired
	private DreamActivityBuilder dreamActivityBuilder;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> addDream(@RequestBody DreamActivityRequest request) {
		logger.debug("request:{}", JSON.toJSONString(request));

		UserModel user = userService.selectByUsername(request.getUsername());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("no such username!");
		}
		String username = request.getUsername();
		String datetime = request.getDatetime();
		ActivityModel activity = dreamActivityBuilder.build(username, datetime, request.getContent());
		logger.debug("activity:{}", JSON.toJSONString(activity));
		activityService.create(activity);
		return ResponseEntity.ok(activity);
	}

	@RequestMapping(value = "/list")
	public String queryDream(Map<String, Object> model) {
		List<ActivityModel> list = activityService.selectByCondition(null, ActivityType.DREAM.name(), null, null);
		model.put("list", list);
		return "dream/list";
	}

}
