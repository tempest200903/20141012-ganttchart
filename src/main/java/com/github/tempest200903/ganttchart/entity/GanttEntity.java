package com.github.tempest200903.ganttchart.entity;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

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
	@NonNull
	private List<TaskEntity> taskEntityList;

}
