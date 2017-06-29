package com.turtlebone.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration 
public class RetryConfig {
	@Bean  
    public RetryTemplate getRetryTemplate(){  
		RetryTemplate template = new RetryTemplate();
		
		SimpleRetryPolicy policy = getSimpleRetryPolicy();
		template.setRetryPolicy(policy);
		
		return template;
    }
	
	@Bean
	public SimpleRetryPolicy getSimpleRetryPolicy() {
		SimpleRetryPolicy policy = new SimpleRetryPolicy();
		policy.setMaxAttempts(2);
		return policy;
	}
}
