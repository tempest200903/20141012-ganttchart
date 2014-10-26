package com.github.tempest200903.ganttchart.entity;

import java.util.ArrayList;
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
		GanttEntity ganttEntity = new GanttEntity();
		for (int i = 0; i < 8; i++) {
			TaskEntity taskEntity = ganttEntity.createSampleTaskEntity();
			taskEntity.setName(String.format("task%d", i));
		}
		return ganttEntity;
	}

	TaskEntity createSampleTaskEntity() {
		String name = "task" + System.currentTimeMillis();
		TaskConstraintType constraintType = TaskConstraintType.AS_SOON_AS_POSSIBLE;
		TaskCalendarEntity taskCalendar = new TaskCalendarEntity();
		TaskEntity taskEntity = new TaskEntity(name, constraintType,
				taskCalendar, 1000 * 60 * 60 * 24, this);
		taskEntityList.add(taskEntity);
		return taskEntity;
	}

	private ProjectEntity projectEntity;

	/**
	 * タスク。
	 */
	@NonNull
	private List<TaskEntity> taskEntityList = new ArrayList<>();

	public GanttEntity() {
		super();
	}

}
