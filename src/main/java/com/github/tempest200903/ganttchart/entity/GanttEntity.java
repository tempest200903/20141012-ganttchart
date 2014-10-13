package com.github.tempest200903.ganttchart.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lombok.Data;
import lombok.NonNull;

import com.google.common.collect.Lists;

/**
 * ガント。
 * 
 * @author tempest200903
 *
 */
@Data
public class GanttEntity {
	
	static Date createBeginDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2014, 2, 3);
		Date beginDate = calendar.getTime();
		return beginDate;
	}

	public static GanttEntity createSampleGanttEntity() {
		List<TaskEntity> taskEntityList = Lists.newArrayList(
				createTaskEntity(), createTaskEntity(), createTaskEntity(),
				createTaskEntity(), createTaskEntity(), createTaskEntity(),
				createTaskEntity(), createTaskEntity());
		GanttEntity ganttEntity = new GanttEntity(taskEntityList);
		return ganttEntity;
	}

	static TaskEntity createTaskEntity() {
		String name = "task" + System.currentTimeMillis();
		Date beginDate = createBeginDate();
		Date endDate = createBeginDate();
		TaskConstraintType constraintType = TaskConstraintType.AS_SOON_AS_POSSIBLE;
		TaskCalendarEntity taskCalendar = new TaskCalendarEntity();

		TaskEntity taskEntity = new TaskEntity(name, beginDate, endDate,
				constraintType, taskCalendar);
		return taskEntity;
	}

	private ProjectEntity projectEntity;

	/**
	 * タスク。
	 */
	@NonNull
	private List<TaskEntity> taskEntityList;

	public GanttEntity(List<TaskEntity> taskEntityList) {
		super();
		this.taskEntityList = taskEntityList;
	}

}
