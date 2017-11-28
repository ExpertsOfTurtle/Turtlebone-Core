package com.turtlebone.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.turtlebone.core.bean.ChooseInfo;
import com.turtlebone.core.bean.CompleteProblemRequest;
import com.turtlebone.core.bean.CreateTaskRequest;
import com.turtlebone.core.bean.InsertProblemRequest;
import com.turtlebone.core.bean.QueryProblemRequest;
import com.turtlebone.core.builder.activity.CFActivityBuilder;
import com.turtlebone.core.constants.ITaskStatus;
import com.turtlebone.core.constants.ITaskType;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.model.ProblemModel;
import com.turtlebone.core.model.TaskModel;
import com.turtlebone.core.model.TaskUserModel;
import com.turtlebone.core.model.UserModel;
import com.turtlebone.core.service.ActivityService;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;
import com.turtlebone.core.service.ProblemService;
import com.turtlebone.core.service.TaskService;
import com.turtlebone.core.service.TaskUserService;
import com.turtlebone.core.service.UserService;
import com.turtlebone.core.util.BeanCopyUtils;
import com.turtlebone.core.util.DateUtil;
import com.turtlebone.core.util.StringUtil;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="/task")
public class TaskController {
	private static Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskUserService taskUserService;
		
	@RequestMapping(value="/queryMyTask", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> queryMyTask(ServletRequest req) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String username = (String)request.getAttribute("username");
		List<TaskModel> list = taskService.selectPage(null, null, username, null, null, null);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request, ServletRequest req) {
		HttpServletRequest httpReq = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String username = (String)httpReq.getAttribute("username");
		
		if (StringUtil.isEmpty(username)) {
			logger.warn("Please login first");
			return ResponseEntity.ok("Plz login");
		}
		
		TaskModel taskModel = new TaskModel();
		taskModel.setTitle(request.getTitle());
		taskModel.setContent(request.getContent());
		taskModel.setCreator(username);
		taskModel.setDeadline(taskModel.getDeadline());
		taskModel.setPunishmentId(taskModel.getPunishmentId());
		taskModel.setStatus(ITaskStatus.NEW);
		taskModel.setType(ITaskType.NORMAL);
		
		int id = taskService.create(taskModel);
		for (String user : request.getOwner()) {
			TaskUserModel taskUserModel = new TaskUserModel();
			taskUserModel.setTaskid(id);
			taskUserModel.setUsername(user);
			taskUserModel.setStatus(ITaskStatus.NEW);
			taskUserModel.setDeadline(taskModel.getDeadline());
			taskUserModel.setAssigndatetime(DateUtil.getDateTime());
			taskUserService.create(taskUserModel);
		}
		
		return ResponseEntity.ok(taskModel);
	}	
	
}
