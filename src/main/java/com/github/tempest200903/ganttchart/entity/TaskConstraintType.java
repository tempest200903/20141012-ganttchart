package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

/**
 * タスク制約の種類。
 * 
 * @author tempest200903
 *
 */
public abstract class TaskConstraintType {

	abstract Date getStartDate(TaskEntity taskEntity);

	abstract Date getFinishDate(TaskEntity taskEntity);

	Date addDurationToStartDate(TaskEntity taskEntity) {
		Date startDate = taskEntity.getStartDate();
		long duration = taskEntity.getDuration();
		return new Date(startDate.getTime() + duration);
	}

}
