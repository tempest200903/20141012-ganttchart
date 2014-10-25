package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

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
	@NonNull
	private Date beginDate;

	/**
	 * 終了日付。
	 */
	@NonNull
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
	 * 期間。単位は秒。計算不能な場合は null 。
	 * 
	 * @return
	 */
	public Long getDuration() {
		return finishDate.getTime() - beginDate.getTime();
	}

}
