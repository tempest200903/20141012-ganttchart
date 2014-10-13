package com.github.tempest200903.ganttchart.gui;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;

import lombok.NonNull;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

/**
 * @author tempest200903
 *
 */
class GanttChart extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NonNull
	private GanttEntity ganttEntity;

	private DateLinePainter dateLinePainter = new DateLinePainterType1();

	GanttChart(GanttEntity ganttEntity) {
		super();
		this.ganttEntity = ganttEntity;
	}

	private List<Calendar> createCalendarList() {
		List<Calendar> dateList = Lists.newArrayList();
		Date beginDate = ganttEntity.getProjectEntity().getBeginDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		for (int i = 0; i < 14; i++) {
			dateList.add((Calendar) calendar.clone());
			calendar.add(Calendar.DATE, 1);
		}
		return dateList;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintDateLine(g);
		paintTaskEntityList(g);
	}

	/**
	 * 日付ラインを描画する。
	 * 
	 * @param g
	 */
	private void paintDateLine(Graphics g) {
		List<Calendar> calendarList = createCalendarList();
		dateLinePainter.paintDateLine(g, calendarList);
	}

	private void paintTaskEntity(Graphics g, TaskEntity taskEntity) {
		Date beginDate = taskEntity.getBeginDate();
		Date endDate = taskEntity.getEndDate();
		// TODO TBD
	}

	/**
	 * タスクリストを描画する。
	 * 
	 * @param g
	 */
	private void paintTaskEntityList(Graphics g) {
		List<TaskEntity> taskEntityList = this.ganttEntity.getTaskEntityList();
		for (int i = 0; i < taskEntityList.size(); i++) {
			TaskEntity taskEntity = taskEntityList.get(i);
			paintTaskEntity(g, taskEntity);
		}
	}

}
