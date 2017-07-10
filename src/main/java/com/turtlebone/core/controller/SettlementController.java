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

import com.turtlebone.core.enums.settlement.service.CFSettlementService;

@Controller
@EnableAutoConfiguration
@RequestMapping(value="settlement")
public class SettlementController {
	private static Logger logger = LoggerFactory.getLogger(SettlementController.class);
	
	@Autowired
	private CFSettlementService cfSettlementService;
	
	@RequestMapping(value="/daily/{username}")
	public @ResponseBody ResponseEntity<?> settleWithDaily(@PathVariable("username") String username) {
		logger.debug("[Settlement daily]username={}", username);
		cfSettlementService.settleWithDaily(username);
		return ResponseEntity.ok("OK");
	}
	@RequestMapping(value="/normal/{username}")
	public @ResponseBody ResponseEntity<?> settleWithNormal(@PathVariable("username") String username) {
		logger.debug("[Settlement daily]username={}", username);
		cfSettlementService.settleWithNormal(username);
		return ResponseEntity.ok("OK");
	}
}
