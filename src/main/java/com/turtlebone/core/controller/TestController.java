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
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;
import com.turtlebone.core.util.BeanCopyUtils;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="/test")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private OptionGroupService optionGroupService;
	
	@Autowired
	private OptionsService optionsService;
	
	@RequestMapping(value="/test1", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> test1(@RequestBody ChooseInfo chooseInfo) {
		System.out.println(JSON.toJSONString(chooseInfo));
		
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/test2/{name}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> test2(@RequestBody ChooseInfo chooseInfo, 
			@PathVariable("name") String name) {
		System.out.println(JSON.toJSONString(chooseInfo));
		System.out.println(name);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(name);
	}
}
