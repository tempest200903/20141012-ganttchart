package com.github.tempest200903.ganttchart.gui;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.github.tempest200903.ganttchart.entity.GanttEntity;

class GanttTableColumnModel extends DefaultTableColumnModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GanttTableColumnModel(GanttEntity ganttEntity) {
		TableColumn column0 = new TableColumn();
		column0.setPreferredWidth(100);
		addColumn(column0);
	}

}
