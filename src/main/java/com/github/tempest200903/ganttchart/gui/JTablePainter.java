package com.github.tempest200903.ganttchart.gui;

import javax.swing.JTable;

class JTablePainter implements TablePainter {

	JTable table;

	JTablePainter(JTable table) {
		super();
		this.table = table;
	}

	@Override
	public int getHeaderHeight() {
		return table.getTableHeader().getHeight();
	}

}
