package com.turtlebone.core.bean;

import com.turtlebone.core.enums.sudoku.SudokuLevel;

import lombok.Data;

@Data
public class SudokuActivity {
	private String username;
	private String datetime;
	private Integer usetime;
	private SudokuLevel level;
	private Integer gameId;
	private Integer rank;
	private Integer problemId;
}
