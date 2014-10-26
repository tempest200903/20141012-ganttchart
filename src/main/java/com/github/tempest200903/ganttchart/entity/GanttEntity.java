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

	static Calendar sampleCalendar = Calendar
			.getInstance(TimeZone.getDefault());

	static {
		sampleCalendar.set(2014, 2, 3);
	}

	static Date createSampleStartDate() {
		sampleCalendar.add(Calendar.DATE, 1);
		return sampleCalendar.getTime();
	}

	static Date createSampleFinishDate() {
		sampleCalendar.add(Calendar.DATE, 1);
		return sampleCalendar.getTime();
	}

	public static GanttEntity createSampleGanttEntity() {
		List<TaskEntity> taskEntityList = Lists.newArrayList(
				createSampleTaskEntity(), createSampleTaskEntity(),
				createSampleTaskEntity(), createSampleTaskEntity(),
				createSampleTaskEntity(), createSampleTaskEntity(),
				createSampleTaskEntity(), createSampleTaskEntity());
		GanttEntity ganttEntity = new GanttEntity(taskEntityList);
		return ganttEntity;
	}

	static TaskEntity createSampleTaskEntity() {
		String name = "task" + System.currentTimeMillis();
		Date beginDate = createSampleStartDate();
		Date endDate = createSampleFinishDate();
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
