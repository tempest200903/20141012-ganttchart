package com.github.tempest200903.ganttchart.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

/**
 * タスク。
 * 
 * @author tempest200903
 *
 */
@Data
public class TaskEntity {

	/**
	 * 名前。
	 */
	@NonNull
	private String name;

	/**
	 * 作業時間。
	 */
	private int workTime;

	/**
	 * 開始日付。
	 */
	private Date startDate;

	/**
	 * 終了日付。
	 */
	private Date finishDate;

	/**
	 * 制約の種類。
	 */
	@NonNull
	private TaskConstraintType constraintType;

	/**
	 * 制約の指定日。
	 */
	private Date constraintDate;

	/**
	 * 期限。
	 */
	private Date deadline;

	/**
	 * タスクカレンダー。
	 */
	@NonNull
	private TaskCalendarEntity taskCalendar;

	/**
	 * 期間。単位は秒。
	 */
	@NonNull
	private long duration;

	@NonNull
	private GanttEntity ganttEntity;

	private List<TaskEntity> predecessors = new ArrayList<>();

	public Date getStartDate() {
		return constraintType.getStartDate(this);
	}

	public Date getFinishDate() {
//		return finishDate;
		return constraintType.getFinishDate(this);
	}

	Date getProjectStartDate() {
		return getGanttEntity().getProjectEntity().getStartDate();
	}

}
