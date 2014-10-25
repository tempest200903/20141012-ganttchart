package com.github.tempest200903.ganttchart.gui;

import javax.swing.JTable;

class JTablePainter extends TablePainter {

	JTable table;

	JTablePainter(JTable table) {
		super();
		this.table = table;
	}

	@Override
	int getHeaderHeight() {
		return table.getTableHeader().getHeight();
	}

	@Override
	int getRowHeight() {
		return table.getRowHeight();
	}

}
