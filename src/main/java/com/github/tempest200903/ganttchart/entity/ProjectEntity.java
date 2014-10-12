package com.github.tempest200903.ganttchart.entity;


import java.util.Date;

import lombok.Data;
import lombok.NonNull;

/**
 * プロジェクト。
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
