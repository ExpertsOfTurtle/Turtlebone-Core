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
	public String getMainPage() {
		logger.debug("go to index.vm");
		return "index";
	}

}
