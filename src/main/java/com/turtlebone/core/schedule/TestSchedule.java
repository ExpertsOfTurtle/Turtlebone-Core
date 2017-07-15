package com.turtlebone.core.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestSchedule {
	private static Logger logger = LoggerFactory.getLogger(TestSchedule.class);
	
	@Scheduled(cron="0 0/1 * * * ?") 
    public void executeFileDownLoadTask() {

        // 间隔2分钟,执行工单上传任务     
        Thread current = Thread.currentThread();  
        System.out.println("定时任务1:"+current.getId());    
        logger.info("定时任务:{}", current.getId());
    }
}
