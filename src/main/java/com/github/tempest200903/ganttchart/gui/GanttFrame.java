package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;

class GanttFrame extends JInternalFrame {

	public static void main(String[] args) {
		GanttEntity sampleGanttEntity = MainFrame.createSampleGanttEntity();
		GanttFrame ganttFrame = new GanttFrame(sampleGanttEntity);
		JTable table = ganttFrame.createTable();
		table.setSize(new Dimension(400, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 800, 600);
		frame.setVisible(true);
	}

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
		JComponent tablePane = createTablePane();
		splitPane.add(tablePane);
		splitPane.add(new JLabel("dummy"));
		splitPane.setDividerLocation(600);
		return splitPane;
	}

	private JTable createTable() {
		List<TaskEntity> taskEntityList = ganttEntity.getTaskEntityList();
		JTable table = new JTable(taskEntityList.size(), 2);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 列ヘッダを用意する。
		TableColumn column0 = table.getTableHeader().getColumnModel()
				.getColumn(0);
		column0.setHeaderValue("name");

		// 行を用意する。
		for (int rowIndex = 0; rowIndex < taskEntityList.size(); rowIndex++) {
			TaskEntity taskEntity = taskEntityList.get(rowIndex);
			String taskName = taskEntity.getName();
			table.getModel().setValueAt(taskName, rowIndex, 0);
		}

		return table;
	}

	private JComponent createTablePane() {
		JTable table = createTable();
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

}
