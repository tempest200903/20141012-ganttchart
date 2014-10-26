package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

/**
 * 指定日以降に終了
 * @author tempest200903
 *
 */
class TaskConstraintTypeFinishNoEarlierThan extends TaskConstraintType {

	@Override
	Date getStartDate(TaskEntity taskEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Date getFinishDate(TaskEntity taskEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
