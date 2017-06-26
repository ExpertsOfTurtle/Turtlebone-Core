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
import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;
import com.turtlebone.core.service.OptionGroupService;
import com.turtlebone.core.service.OptionsService;

@Controller
@EnableAutoConfiguration
public class ChooseController {
	private static Logger logger = LoggerFactory.getLogger(ChooseController.class);
	
	@Autowired
	private OptionGroupService optionGroupService;
	
	@Autowired
	private OptionsService optionsService;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> create(ChooseInfo chooseInfo) {
		if (chooseInfo.getGroup() == null) {
			logger.error("group is null");
			return ResponseEntity.ok("Group is null");
		} else if (chooseInfo.getOptions() == null || chooseInfo.getOptions().size() == 0) {
			logger.error("options is null");
			return ResponseEntity.ok("options is empty");
		}
		
		optionGroupService.create(chooseInfo.getGroup());
		optionsService.batchInsert(chooseInfo.getOptions());
		
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> modify(ChooseInfo chooseInfo) {
		if (chooseInfo.getGroup() == null) {
			logger.error("group is null");
			return ResponseEntity.ok("Group is null");
		} else if (chooseInfo.getOptions() == null || chooseInfo.getOptions().size() == 0) {
			logger.error("options is null");
			return ResponseEntity.ok("options is empty");
		}
		Integer gid = chooseInfo.getGroup().getGroupid();
		OptionGroupModel group = optionGroupService.findByPrimaryKey(gid);
		if (group == null) {
			logger.error("no such group!");
			return ResponseEntity.ok("no such group!");
		}
		optionsService.deleteByGroupId(gid);
		optionsService.batchInsert(chooseInfo.getOptions());
		
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value="/delete/{groupId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("groupId") Integer groupId) {
		logger.debug("Delete group {}", groupId);
		
		int rt = optionGroupService.deleteByPrimaryKey(groupId);
		rt += optionsService.deleteByGroupId(groupId);
		
		return ResponseEntity.ok(rt);
	}
	
	@RequestMapping(value="/query", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> query() {
		
		List<OptionGroupModel> groupList = optionGroupService.selectAll();
		List<OptionsModel> optionList = optionsService.selectAll();
		Map<Integer, ChooseInfo> map = new HashMap<>();
		for (OptionGroupModel group : groupList) {
			ChooseInfo info = new ChooseInfo();
			info.setGroup(group);
			info.setOptions(new ArrayList<OptionsModel>());
			map.put(group.getGroupid(), info);
		}
		
		for (OptionsModel opt : optionList) {
			Integer gid = opt.getGroupid();
			ChooseInfo info = map.get(gid);
			if (info == null) {
				logger.warn("groupId {} not exist", gid);
				continue;
			}
			info.getOptions().add(opt);
		}
		
		List<ChooseInfo> result = new ArrayList<>();
		for (Entry<Integer, ChooseInfo> entry : map.entrySet()) {
			result.add(entry.getValue());
		}
		
		return ResponseEntity.ok(result);
	}
	
}
