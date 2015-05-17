package com.github.tempest200903.ganttchart.gui;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.TaskDependencyEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

class TaskTable {

    /**
     * date を表示用文字列に変換する。
     * 
     * @param date
     * @return
     */
    static String toDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        return dateFormat.format(date);
    }

    /**
     * duration を表示用文字列に変換する。
     * 
     * @param durationMilliseconds
     * @return
     */
    static String toDurationString(long durationMilliseconds) {
        double durationDays = ((double) durationMilliseconds)
                / TimeUnit.DAYS.toMillis(1);
        String durationString = String.format("%.2f", durationDays);
        return durationString;
    }

    private JTable table;

    private GanttEntity ganttEntity;

    TaskTable(GanttEntity ganttEntity1) {
        super();
        ganttEntity = ganttEntity1;
        this.table = createTable(ganttEntity1);
    }

    private List<String> createHeaderValueList() {
        String[] headerValueArray = { "number" // 番号。
                , "name" // 名前。
                , "duration (days)" // 期間。
                , "start date" // 開始日時。
                , "finish date" // 終了日時。
                , "predecessor" // 先行タスク。
        };
        return Lists.newArrayList(headerValueArray);
    }

    private JTable createTable(GanttEntity ganttEntity) {
        List<String> headerValueList = createHeaderValueList();
        List<TaskEntity> taskEntityList = ganttEntity.getTaskEntityList();
        JTable table0 = new JTable(taskEntityList.size(),
                headerValueList.size());
        table0.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        initialzeColumn(table0, headerValueList);
        initialzeRow(table0, taskEntityList);
        // #4 predecessor を変更したら TimelineChart を再描画する。
        table0.getModel().addTableModelListener(new TaskTableTableModelListener());
        return table0;
    }

    JComponent createTablePane() {
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    int getHeaderHeight() {
        JTableHeader tableHeader = table.getTableHeader();
        assert tableHeader != null;
        return tableHeader.getHeight();
    }

    int getRowHeight() {
        return table.getRowHeight();
    }

    Dimension getTableHeaderSize() {
        return table.getTableHeader().getSize();
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
            long durationMilliseconds = taskEntity.getDuration();
            String durationString = toDurationString(durationMilliseconds);
            table.getModel().setValueAt(durationString, rowIndex, 2);

            // startDate
            Date startDate = taskEntity.getStartDate();
            String startDateString = toDateString(startDate);
            table.getModel().setValueAt(startDateString, rowIndex, 3);

            // finishDate
            Date finishDate = taskEntity.getFinishDate();
            String endDate = toDateString(finishDate);
            table.getModel().setValueAt(endDate, rowIndex, 4);

            // predecessorList
            String predecessorList = toDisplayString(taskEntity
                    .getPredecessorList());
            table.getModel().setValueAt(predecessorList, rowIndex, 5);
        }
    }

    void setPreferredSizeOfHeader(Dimension preferredSize) {
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(preferredSize);
    }

    private String toDisplayString(List<TaskDependencyEntity> predecessorList) {
        StringBuilder s = new StringBuilder();
        for (TaskDependencyEntity taskDependencyEntity : predecessorList) {
            int number = taskDependencyEntity.getFrom().getNumber() + 1;
            s.append(number + " ");
        }
        return s.toString();
    }

    class TaskTableTableModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            // table から duration を読み取って ganttModel へ反映する。
            TableModel source = (TableModel) e.getSource();
            for (int rowIndex = 0; rowIndex < source.getRowCount(); rowIndex++) {
                int columnIndex = 2;
                source.getColumnName(columnIndex);
                String string = (String) source.getValueAt(rowIndex,
                        columnIndex);
                System.out.println("rowIndex =: " + rowIndex);
                System.out.println("columnIndex =: " + columnIndex);
                System.out.println("value =: " + string);
                double value = Double.parseDouble(string);
                long duration = (long) (value * TimeUnit.DAYS.toMillis(1));
                TaskEntity taskEntity = ganttEntity.getTaskEntityList().get(
                        rowIndex);
                taskEntity.setDuration(duration);
                System.out.println("taskEntity =: " + taskEntity);
            }

        }
    }

}
