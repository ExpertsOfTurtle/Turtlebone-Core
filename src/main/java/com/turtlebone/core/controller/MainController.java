package com.turtlebone.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@RequestMapping(value="/query/{username}")
	public @ResponseBody ResponseEntity<?> queryByProductIdList(@PathVariable("username") String username) {
		logger.debug("username={}", username);
		String rs = "OH " + username;
		return ResponseEntity.ok(rs);
	}
	
	/*
	 * 进入主页
	 * */
	@RequestMapping(value="/main")
	public String getMainPage(Map<String, Object> model) {
		logger.debug("go to index.vm");
		model.put("wf", "DFSDFS");
		return "index";
	}

	@RequestMapping(value="/turtlebone")
	public String turtlebone(Map<String, Object> model) {
		logger.debug("go to turtlebone.vm");
		return "turtlebone";
	}
	
	@RequestMapping(value="/main/dream/{page}")
	public String dreamPage(Map<String, Object> model, @PathVariable("page") String page) {
		logger.debug("go to {}.vm", page);
		return "dream/" + page;
	}
	@RequestMapping(value="/main/decide/{page}")
	public String decidePage(Map<String, Object> model, @PathVariable("page") String page) {
		logger.debug("go to {}.vm", page);
		return "decide/" + page;
	}
}
