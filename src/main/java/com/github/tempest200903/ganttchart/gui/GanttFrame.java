package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.TextAction;

import com.github.tempest200903.ganttchart.entity.GanttEntity;

class GanttFrame extends JInternalFrame implements PropertyChangeListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private GanttEntity ganttEntity;

    private TaskTable taskTable;

    private TimelineChart timelineChart;

    private JScrollPane timelineScrollPane;

    private TextAction zoomInAction = new TextAction("zoom in") {
        /** . */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            timelineChart.zoomIn();
        }
    };

    private TextAction zoomOutAction = new TextAction("zoom out") {
        /** . */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            timelineChart.zoomOut();
        }
    };

    public GanttFrame(GanttEntity ganttEntity1) {
        super();
        ganttEntity = ganttEntity1;
        ganttEntity.addPropertyChangeListener(this);
        taskTable = new TaskTable(ganttEntity1);
        timelineChart = new TimelineChart(ganttEntity1, new JTablePainter(
                taskTable));

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

    private JComponent createTablePane() {
        return taskTable.createTablePane();
    }

    private JScrollPane createTimelineChartPane() {
        timelineScrollPane = new JScrollPane(timelineChart,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        timelineChart.setPreferredSize(screenSize);
        return timelineScrollPane;
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
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("evt =: " + evt);
        if (ganttEntity.equals(evt.getSource())) {
            System.out.println("evt.getSource() =: " + evt.getSource());
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (isVisible()) {
            printSize();
            Dimension tableHeaderSize = taskTable.getTableHeaderSize();
            int preferredWidth = (int) tableHeaderSize.getWidth();
            int preferredHeight = (int) (tableHeaderSize.getHeight() + 40);
            Dimension preferredSize = new Dimension(preferredWidth,
                    preferredHeight);
            taskTable.setPreferredSizeOfHeader(preferredSize);
        }
    }

}
