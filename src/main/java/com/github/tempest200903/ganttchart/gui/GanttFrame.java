package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

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
		setLayout(new BorderLayout());

		JSplitPane splitPane = createSplitPane();
		getContentPane().add(splitPane);
	}

	private JSplitPane createSplitPane() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JTable table = createTable();
		splitPane.add(table);
		splitPane.add(new JLabel("dummy"));
		splitPane.setDividerLocation(600);
		return splitPane;
	}

	private JTable createTable() {
		TableModel tableModel = new GanttTableModel(this.ganttEntity);
		TableColumnModel tableColumnModel = new GanttTableColumnModel(
				this.ganttEntity);
		JTable table = new JTable(tableModel, tableColumnModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		return table;
	}

}
