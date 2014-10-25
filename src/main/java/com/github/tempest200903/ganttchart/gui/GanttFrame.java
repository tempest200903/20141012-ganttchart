package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TextAction;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

class GanttFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GanttEntity ganttEntity;

	private TimelineChart timelineChart;

	private TextAction zoomOutAction = new TextAction("zoom out") {
		/** . */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			timelineChart.zoomOut();
		}
	};
	private TextAction zoomInAction = new TextAction("zoom in") {
		/** . */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			timelineChart.zoomIn();
		}
	};

	private JScrollPane timelineScrollPane;

	private JTable table;

	public GanttFrame(GanttEntity ganttEntity) {
		super();
		this.ganttEntity = ganttEntity;
		this.table = createTable();
		timelineChart = new TimelineChart(ganttEntity, new JTablePainter(table));

		setTitle("Gantt");
		setLayout(new BorderLayout());

		JSplitPane splitPane = createSplitPane();
		getContentPane().add(splitPane);

		JMenuBar menubar = createMenuBar();
		setJMenuBar(menubar);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem zoomOutMenu = new JMenuItem(zoomOutAction);
		JMenuItem zoomInMenu = new JMenuItem(zoomInAction);
		menuBar.add(zoomOutMenu);
		menuBar.add(zoomInMenu);
		return menuBar;
	}

	private JSplitPane createSplitPane() {
		JScrollPane timelineChartPane = createTimelineChartPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JComponent tablePane = createTablePane();
		splitPane.add(tablePane);
		splitPane.add(timelineChartPane);
		splitPane.setDividerLocation(600);
		return splitPane;
	}

	private JTable createTable() {
		List<String> headerValueList = Lists.newArrayList();
		headerValueList.add("number"); // 番号
		headerValueList.add("name"); // 名前
		headerValueList.add("duration"); // 期間
		headerValueList.add("start date"); // 開始日時
		headerValueList.add("finish date"); // 終了日時

		List<TaskEntity> taskEntityList = ganttEntity.getTaskEntityList();
		JTable table0 = new JTable(taskEntityList.size(),
				headerValueList.size());
		table0.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		initialzeColumn(table0, headerValueList);
		initialzeRow(table0, taskEntityList);
		return table0;
	}

	private JComponent createTablePane() {
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}

	private JScrollPane createTimelineChartPane() {
		timelineScrollPane = new JScrollPane(timelineChart,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		timelineChart.setPreferredSize(screenSize);
		return timelineScrollPane;
	}

	private void initialzeColumn(JTable table, List<String> headerValueList) {
		// 列ヘッダを用意する。
		TableColumnModel columnModel = table.getTableHeader().getColumnModel();
		for (int i = 0; i < headerValueList.size(); i++) {
			TableColumn column = columnModel.getColumn(i);
			column.setHeaderValue(headerValueList.get(i));
		}
	}

	private void initialzeRow(JTable table, List<TaskEntity> taskEntityList) {
		// 行を用意する。
		for (int rowIndex = 0; rowIndex < taskEntityList.size(); rowIndex++) {
			TaskEntity taskEntity = taskEntityList.get(rowIndex);

			// number
			String value0 = String.valueOf(rowIndex + 1);
			table.getModel().setValueAt(value0, rowIndex, 0);

			// name
			String taskName = taskEntity.getName();
			table.getModel().setValueAt(taskName, rowIndex, 1);

			// duration
			String duration = String.valueOf(taskEntity.getDuration());
			table.getModel().setValueAt(duration, rowIndex, 2);

			// beginDate
			String beginDate = String.valueOf(taskEntity.getBeginDate());
			table.getModel().setValueAt(beginDate, rowIndex, 3);

			// finish
			String endDate = String.valueOf(taskEntity.getFinishDate());
			table.getModel().setValueAt(endDate, rowIndex, 4);
		}
	}

	/**
	 * デバッグ用。
	 */
	private void printSize() {
		System.out.println("timelineChart.getSize() =: "
				+ timelineChart.getSize());
		System.out.println("timelineChart.getMinimumSize() =: "
				+ timelineChart.getMinimumSize());
		System.out.println("timelineChart.getPreferredSize() =: "
				+ timelineChart.getPreferredSize());

		System.out.println("scrollPane.getSize() =: "
				+ timelineScrollPane.getSize());
		System.out.println("scrollPane.getMinimumSize() =: "
				+ timelineScrollPane.getMinimumSize());
		System.out.println("scrollPane.getPreferredSize() =: "
				+ timelineScrollPane.getPreferredSize());
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (isVisible()) {
			printSize();
			JTableHeader header = table.getTableHeader();
			int preferredWidth = header.getWidth();
			int preferredHeight = header.getHeight() + 40;
			Dimension preferredSize = new Dimension(preferredWidth,
					preferredHeight);
			header.setPreferredSize(preferredSize);
		}
	}

}
