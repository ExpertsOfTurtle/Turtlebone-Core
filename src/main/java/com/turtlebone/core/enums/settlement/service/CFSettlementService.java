package com.turtlebone.core.enums.settlement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turtlebone.core.enums.problem.ProblemType;
import com.turtlebone.core.model.ProblemModel;
import com.turtlebone.core.model.UserModel;
import com.turtlebone.core.service.ProblemService;
import com.turtlebone.core.service.UserService;
import com.turtlebone.core.util.DateUtil;

@Service
public class CFSettlementService {
	
	@Autowired
	private ProblemService problemService;
	@Autowired
	private UserService userService;

	/*
	 * For those daily problem, 2RMB/Problem
	 * */
	public boolean settleWithDaily(String username) {
		String type = ProblemType.DAILY.getValue();
		String status = "0";
		String from = DateUtil.getLastMonday();
		String to = DateUtil.getLastSunday();
		List<ProblemModel> list = problemService.selectByCondition(username, type, status, from, to);
		
		int amount = list.size() * 2;
		updateBalance(username, amount);
		return true;
	}
	
	/*
	 * For those normal problem, price is 2x,3x,4x,...
	 * */
	public boolean settleWithNormal(String username) {
		String type = ProblemType.NORMAL.getValue();
		String status = "0";
		String from = DateUtil.getLastMonday();
		String to = DateUtil.getLastSunday();
		List<ProblemModel> list = problemService.selectByCondition(username, type, status, from, to);
		
		int amount = 0;
		int n = list.size();
		int unit = 2;
		while (n >= 5) {
			amount += unit * 5;
			unit++;
			n -= 5;
		}
		amount += unit * n; 
		updateBalance(username, amount);
		return true;
	}
	
	/*
	 * For those senior problem, price is 3x,4x,5x...
	 * */
	public boolean settleWithSenior(String username) {
		String type = ProblemType.SENIOR.getValue();
		String status = "0";
		String from = DateUtil.getLastMonday();
		String to = DateUtil.getLastSunday();
		List<ProblemModel> list = problemService.selectByCondition(username, type, status, from, to);
		
		int amount = 0;
		int n = list.size();
		int unit = 3;
		while (n >= 5) {
			amount += unit * 5;
			unit++;
			n -= 5;
		}
		amount += unit * n; 
		updateBalance(username, amount);
		return true;
	}
	
	/*
	 * For those PrentendB problem, price is 4x,5x,6x...
	 * */
	public boolean settleWithPretendB(String username) {
		String type = ProblemType.PRETENDB.getValue();
		String status = "0";
		String from = DateUtil.getLastMonday();
		String to = DateUtil.getLastSunday();
		List<ProblemModel> list = problemService.selectByCondition(username, type, status, from, to);
		
		int amount = 0;
		int n = list.size();
		int unit = 4;
		while (n >= 5) {
			amount += unit * 5;
			unit++;
			n -= 5;
		}
		amount += unit * n; 
		updateBalance(username, amount);
		return true;
	}
	
	private void updateBalance(String username, int amount) {
		UserModel user = userService.selectByUsername(username);
		
		Float bal = user.getBalance();
		bal += amount;
		user.setBalance(bal);
		userService.updateByPrimaryKey(user);
	}
}