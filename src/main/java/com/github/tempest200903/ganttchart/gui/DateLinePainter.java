package com.github.tempest200903.ganttchart.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.List;

import com.github.tempest200903.ganttchart.entity.ProjectEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;

abstract class DateLinePainter {

	private TablePainter tablePainter;

	private ProjectEntity projectEntity;

	ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	TablePainter getTablePainter() {
		return tablePainter;
	}

	DateLinePainter(ProjectEntity projectEntity, TablePainter tablePainter) {
		super();
		this.projectEntity = projectEntity;
		this.tablePainter = tablePainter;
	}

	public Rectangle getPaintingBounds() {
		return datelinePaintingBounds;
	}

	protected Rectangle datelinePaintingBounds = new Rectangle();

	abstract void paintDateLine(Graphics g, int headerHeight,
			List<Calendar> calendarList);

	abstract void paintTaskBar(Graphics g, int taskIndex, TaskEntity taskEntity);

}
