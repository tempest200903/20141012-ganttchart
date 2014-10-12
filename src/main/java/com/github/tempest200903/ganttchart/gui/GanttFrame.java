package com.github.tempest200903.ganttchart.gui;

import javax.swing.JInternalFrame;

import com.github.tempest200903.ganttchart.entity.GanttEntity;

class GanttFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GanttEntity ganttEntity;

	public GanttFrame(GanttEntity ganttEntity) {
		super();
		this.ganttEntity = ganttEntity;
		setTitle("Gantt");
	}

}
