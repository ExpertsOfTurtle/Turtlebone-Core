package com.turtlebone.core.bean;

import java.util.List;

import com.turtlebone.core.model.OptionGroupModel;
import com.turtlebone.core.model.OptionsModel;

import lombok.Data;

@Data
public class ChooseInfo {
	private OptionGroupModel group;
	private List<OptionsModel> options;
}
