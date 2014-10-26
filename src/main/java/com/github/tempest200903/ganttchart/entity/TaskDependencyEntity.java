package com.github.tempest200903.ganttchart.entity;

import lombok.Getter;
import lombok.NonNull;

/**
 * タスクの依存関係。
 * 
 * @author tempest200903
 *
 */
public class TaskDependencyEntity {

	TaskDependencyEntity(TaskEntity from, TaskEntity to) {
		super();
		this.from = from;
		this.to = to;
	}

	@Getter
	@NonNull
	TaskEntity from;

	@Getter
	@NonNull
	TaskEntity to;

	// int type; // TODO 未実装。 FF, FS, SF, SS

	// long lag; // TODO 未実装。

}
