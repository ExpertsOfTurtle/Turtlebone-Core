package com.turtlebone.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
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

import com.turtlebone.core.bean.ChooseInfo;
import com.turtlebone.core.bean.CompleteProblemRequest;
import com.turtlebone.core.bean.InsertProblemRequest;
import com.turtlebone.core.bean.QueryProblemRequest;
import com.turtlebone.core.bean.SendEmailRequest;
import com.turtlebone.core.builder.activity.CFActivityBuilder;
import com.turtlebone.core.model.ActivityModel;
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.model.ProblemModel;
import com.turtlebone.core.model.UserModel;
import com.turtlebone.core.service.ActivityService;
import com.turtlebone.core.service.EmailService;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;
import com.turtlebone.core.service.ProblemService;
import com.turtlebone.core.service.UserService;
import com.turtlebone.core.util.BeanCopyUtils;
import com.turtlebone.core.util.DateUtil;
import com.turtlebone.core.util.StringUtil;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/report")
public class ReportController {
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ActivityService activityService;
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/cf/normal", method = RequestMethod.POST)
	public String sendEmail(Map<String, Object> model) {

		String activityType = "COMPLETECF";
		String from = DateUtil.getNDaysLater(-7, "yyyy-MM-dd");
		String to = DateUtil.getNDaysLater(1, "yyyy-MM-dd");
		List<ActivityModel> dfsList = activityService.selectByCondition("DFS", activityType, from, to, null, null);
		List<ActivityModel> couldList = activityService.selectByCondition("Could", activityType, from, to, null, null);

		model.put("dfsList", dfsList);
		model.put("couldList", couldList);
		return "report/cf/normal";
	}

}
