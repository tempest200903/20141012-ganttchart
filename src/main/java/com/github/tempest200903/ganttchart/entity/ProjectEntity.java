package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;

/*
 @startuml ProjectEntity.puml.png
 title ClassDiagram ProjectEntity
 package entity {
 class ProjectEntity{
 String name;
 Date beginDate;
 }


 class GanttEntity{
 }

 class TaskEntity{
 String name;
 int workTime;
 Date beginDate;
 Date endDate;
 TaskConstraintType constraintType;
 Date constraintDate;
 Date deadline;
 TaskCalendarEntity taskCalendar;
 }

 ProjectEntity "1" o- "1" GanttEntity
 GanttEntity "1" o- "1" TaskEntity

 }
 @enduml
 */

/**
 * プロジェクト。
 * 
 * @author tempest200903
 *
 */
@Data
public class ProjectEntity {

	/**
	 * 名前。
	 */
	@NonNull
	private String name;

	/**
	 * 開始日付。
	 */
	@NonNull
	private Date beginDate;

	/**
	 * ガント。
	 */
	@NonNull
	private GanttEntity ganttEntity;

}
