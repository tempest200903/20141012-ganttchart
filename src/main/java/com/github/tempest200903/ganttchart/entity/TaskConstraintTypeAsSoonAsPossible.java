package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

/**
 * できるだけ早く
 * @author tempest200903
 *
 */
class TaskConstraintTypeAsSoonAsPossible extends TaskConstraintType {

	@Override
	Date getStartDate(TaskEntity taskEntity) {
		// TODO Auto-generated method stub
		return taskEntity.getProjectStartDate();
	}

	@Override
	Date getFinishDate(TaskEntity taskEntity) {
		// TODO Auto-generated method stub
		return addDurationToStartDate(taskEntity);
	}

}
