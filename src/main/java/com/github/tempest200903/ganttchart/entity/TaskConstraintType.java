package com.github.tempest200903.ganttchart.entity;

/**
 * タスク制約の種類。
 * 
 * @author tempest200903
 *
 */
public enum TaskConstraintType {
	
	/**
	 * できるだけ早く
	 */
	AS_SOON_AS_POSSIBLE,

	/**
	 * できるだけ遅く
	 */
	AS_LATE_AS_POSSIBLE,

	/**
	 * 指定日に開始
	 */
	MUST_START_ON,

	/**
	 * 指定日に終了
	 */
	MUST_FINISH_ON,

	/**
	 * 指定日以降に開始
	 */
	START_NO_EARLIER_THAN,

	/**
	 * 指定日までに終了
	 */
	START_NO_LATER_THAN,

	/**
	 * 指定日以降に終了
	 */
	FINISH_NO_EARLIER_THAN,

	/**
	 * 指定日までに開始
	 */
	FINISH_NO_LATER_THAN

}
