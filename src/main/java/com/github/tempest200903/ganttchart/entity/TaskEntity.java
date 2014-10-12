package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

import lombok.Data;

/**
 * タスク。
 * 
 * @author tempest200903
 *
 */
@Data
class TaskEntity {

	/**
	 * 名前。
	 */
	private String name;

	/**
	 * 作業時間。
	 */
	private int workTime;

	/**
	 * 開始日付。
	 */
	private Date beginDate;

	/**
	 * 終了日付。
	 */
	private Date endDate;

	/**
	 * 制約の種類。
	 */
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
	private TaskCalendarEntity taskCalendar;

}
