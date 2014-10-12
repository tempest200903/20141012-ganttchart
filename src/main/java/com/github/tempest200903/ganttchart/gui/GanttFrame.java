package com.github.tempest200903.ganttchart.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;

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
		setLayout(new GridLayout(8, 1));

		List<TaskEntity> list = this.ganttEntity.getTaskEntityList();
		for (TaskEntity taskEntity : list) {
			String taskName = taskEntity.getName();
			JLabel label = new JLabel(taskName);
			getContentPane().add(label);
		}
	}

}
