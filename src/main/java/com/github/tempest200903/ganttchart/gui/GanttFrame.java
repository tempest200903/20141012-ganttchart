package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TextAction;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

class GanttFrame extends JInternalFrame {

	public static void main(String[] args) {
		GanttEntity sampleGanttEntity = MainFrame.createSampleGanttEntity();
		new ProjectEntity("name", new Date(),
				Lists.newArrayList(sampleGanttEntity));
		GanttFrame ganttFrame = new GanttFrame(sampleGanttEntity);
		JScrollPane timelineChartPane = ganttFrame.createTimelineChartPane();
		timelineChartPane.getViewport().setSize(new Dimension(1200, 1800));

		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(timelineChartPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 800, 600);
		frame.setVisible(true);
	}

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

	public GanttFrame(GanttEntity ganttEntity) {
		super();
		this.ganttEntity = ganttEntity;

		timelineChart = new TimelineChart(ganttEntity);

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
		// TODO 両方の pane にスクロールバーを表示する。
		return splitPane;
	}

	private JScrollPane createTimelineChartPane() {
		JScrollPane scrollPane = new JScrollPane(timelineChart,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		timelineChart.setSize(new Dimension(300, 400));
		timelineChart.setMinimumSize(new Dimension(300, 400));
		timelineChart.setPreferredSize(new Dimension(300, 400));
		return scrollPane;
	}

	private JTable createTable() {
		List<String> headerValueList = Lists.newArrayList();
		headerValueList.add("number"); // 番号
		headerValueList.add("name"); // 名前
		headerValueList.add("duration"); // 期間
		headerValueList.add("start date"); // 開始日時
		headerValueList.add("finish date"); // 終了日時

		List<TaskEntity> taskEntityList = ganttEntity.getTaskEntityList();
		JTable table = new JTable(taskEntityList.size(), headerValueList.size());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		initialzeColumn(table, headerValueList);
		initialzeRow(table, taskEntityList);
		return table;
	}

	private JComponent createTablePane() {
		JTable table = createTable();
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
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
			String taskName = taskEntity.getName();
			table.getModel().setValueAt(taskName, rowIndex, 0);
		}
	}

}
