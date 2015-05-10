package com.github.tempest200903.ganttchart.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;

import lombok.NonNull;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

/**
 * @author tempest200903
 *
 */
class TimelineChart extends JComponent {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NonNull
    private GanttEntity ganttEntity;

    @NonNull
    private TimelinePainter timelinePainter;

    @NonNull
    private TablePainter tablePainter;

    TimelineChart(GanttEntity ganttEntity, TablePainter tablePainter) {
        super();
        this.ganttEntity = ganttEntity;
        this.tablePainter = tablePainter;
        assert ganttEntity != null : "ganttEntity";
        ProjectEntity projectEntity = ganttEntity.getProjectEntity();
        timelinePainter = new TimelinePainterType1(projectEntity, tablePainter);

        System.out.println("this.tablePainter.getRowHeight() =: "
                + this.tablePainter.getHeaderHeight());
    }

    private List<Calendar> createCalendarList() {
        List<Calendar> dateList = Lists.newArrayList();
        ProjectEntity projectEntity = ganttEntity.getProjectEntity();
        Date startDate = projectEntity.getStartDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        for (int i = 0; i < 14; i++) {
            dateList.add((Calendar) calendar.clone());
            calendar.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Rectangle paintingBounds = paintDateLine(g);
        paintTaskEntityList(g, paintingBounds);
    }

    /**
     * 日付ラインを描画する。
     * 
     * @param g
     * @return
     */
    private Rectangle paintDateLine(Graphics g) {
        List<Calendar> calendarList = createCalendarList();
        int headerHeight = tablePainter.getHeaderHeight();
        timelinePainter.paintDateLine(g, headerHeight, calendarList);
        return timelinePainter.getPaintingBounds();
    }

    /**
     * TaskEntity を描画する。
     * 
     * @param g
     * @param previousPaintingBounds
     * @param taskIndex
     *            TODO
     * @param taskEntity
     * @return 描画領域。
     */
    private Rectangle paintTaskEntity(Graphics g,
            Rectangle previousPaintingBounds, int taskIndex,
            TaskEntity taskEntity) {
        // 描画の準備。
        // FontMetrics fontMetrics = g.getFontMetrics();
        // int fontHeight = fontMetrics.getHeight();
        g.setColor(Color.RED);
        Rectangle currentPaintingBounds = new Rectangle();

        // 下線を描画する。
        int x1 = previousPaintingBounds.x;
        int y1 = previousPaintingBounds.y + previousPaintingBounds.height
                + tablePainter.getRowHeight();
        int x2 = previousPaintingBounds.x + previousPaintingBounds.width;
        int y2 = y1;
        g.drawLine(x1, y1, x2, y2);

        // currentPaintingBounds を計算する。
        currentPaintingBounds.x = x1;
        currentPaintingBounds.y = previousPaintingBounds.y
                + previousPaintingBounds.height;
        currentPaintingBounds.width = x2 - x1;
        currentPaintingBounds.height = y1 - currentPaintingBounds.y;

        timelinePainter.paintTaskBar(g, taskIndex, taskEntity);
        return currentPaintingBounds;
    }

    /**
     * タスクリストを描画する。
     * 
     * @param g
     * @param previousPaintingBounds
     *            描画の基点座標。
     */
    private void paintTaskEntityList(Graphics g,
            Rectangle previousPaintingBounds) {
        List<TaskEntity> taskEntityList = this.ganttEntity.getTaskEntityList();
        Rectangle currentPaintingBounds = previousPaintingBounds;
        for (int taskIndex = 0; taskIndex < taskEntityList.size(); taskIndex++) {
            TaskEntity taskEntity = taskEntityList.get(taskIndex);
            currentPaintingBounds = paintTaskEntity(g, currentPaintingBounds,
                    taskIndex, taskEntity);
        }
    }

    /**
     * ズームインする。
     */
    void zoomIn() {
        // TODO Auto-generated method stub
        System.out.println("zoomIn");
    }

    /**
     * ズームアウトする。
     */
    void zoomOut() {
        // TODO Auto-generated method stub
        System.out.println("zoomOut");

    }

}
