package com.github.tempest200903.ganttchart.entity;

import java.util.List;

import lombok.Data;

/**
 * ガント。
 * 
 * @author tempest200903
 *
 */
@Data
public class GanttEntity {

	/**
	 * タスク。
	 */
	private List<TaskEntity> taskEntity;

}
