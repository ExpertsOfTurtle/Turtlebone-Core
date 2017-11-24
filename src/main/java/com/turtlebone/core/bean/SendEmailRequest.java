package com.turtlebone.core.bean;


import java.util.List;

import lombok.Data;

@Data
public class SendEmailRequest {
	private List<String> addressList;
	private String title;
	private String template;
	private String alias;
}
