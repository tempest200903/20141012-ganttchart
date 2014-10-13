package com.github.tempest200903.ganttchart.gui;

import javax.swing.table.DefaultTableModel;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;

class GanttTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GanttEntity ganttEntity;

	public GanttTableModel(GanttEntity ganttEntity) {
		super();
		assert ganttEntity != null;
		assert ganttEntity.getTaskEntityList() != null;
		this.ganttEntity = ganttEntity;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: // 文字列
			return String.class;
		default:
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getColumnName(int column) {
		return "namd";
	}

	@Override
	public int getRowCount() {
		if (ganttEntity == null) {
			return 0;
		}
		assert ganttEntity.getTaskEntityList() != null;
		return ganttEntity.getTaskEntityList().size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		TaskEntity taskEntity = ganttEntity.getTaskEntityList().get(row);
		return taskEntity.getName();
	}

}
