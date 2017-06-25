package com.turtlebone.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.turtlebone.core")
@MapperScan("com.turtlebone.core.repository") 
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
