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
import com.turtlebone.core.bean.QueryProblemRequest;
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.model.ProblemModel;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;
import com.turtlebone.core.service.ProblemService;
import com.turtlebone.core.util.BeanCopyUtils;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="/CF")
public class CFController {
	private static Logger logger = LoggerFactory.getLogger(CFController.class);
	
	@Autowired
	private ProblemService problemService;
		
	@RequestMapping(value="/queryByDeadline", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> queryByDeadline(@RequestBody QueryProblemRequest request) {
		List<ProblemModel> list = problemService.selectByCondition(request.getUsername(), 
				request.getType(), request.getStatus(), 
				request.getDeadlineFrom(), request.getDeadlineTo(), 
				"deadline");
		return ResponseEntity.ok(list);
	}
	
	
}
