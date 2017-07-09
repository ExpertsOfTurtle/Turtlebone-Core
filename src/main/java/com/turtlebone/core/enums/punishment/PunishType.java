package com.turtlebone.core.enums.punishment;

public enum PunishType {
	CF(1, "Codeforces"),
	STAR(2, "StarBuck")
	;
	private Integer value;
	private String description;
	PunishType(Integer level, String description) {
		this.value = level;
		this.description = description;
	}
}
