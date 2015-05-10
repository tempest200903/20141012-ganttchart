package com.github.tempest200903.ganttchart.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.tempest200903.ganttchart.entity.ProjectEntity;
import com.github.tempest200903.ganttchart.entity.TaskEntity;

/**
 * 1行目が1日、2行目が2時間のスケールで描画するペインタ。
 * 
 * @author tempest200903
 *
 */
class TimelinePainterType1 extends TimelinePainter {

    /**
     * issue #3 TimelineChart タスクの高さをグリッド線よりも低くする。
     */
    private static final int GAP_BETWEEN_GRID_AND_TASK = 2;

    static int[] HOURS = new int[12];

    static {
        for (int i = 0; i < HOURS.length; i++) {
            HOURS[i] = i * 2;
        }
    }

    /**
     * 1日ぶんの幅。 paintDateLine() の中で算出する。
     */
    private int widthPerDay = Integer.MAX_VALUE;

    /**
     * 描画領域のX座標オフセット。固定。
     */
    private int xOffset = 2;

    /**
     * 描画領域のY座標オフセット。 paintDateLine() の中で算出する。
     */
    private int yOffset = Integer.MAX_VALUE;

    TimelinePainterType1(ProjectEntity projectEntity, TablePainter tablePainter) {
        super(projectEntity, tablePainter);
    }

    private int calcXAxis(Date date) {
        long projectBeginTime = getProjectEntity().getStartDate().getTime();
        long relativeTime = date.getTime() - projectBeginTime;
        double dayRate = relativeTime / (1000 * 60 * 60 * 24);
        if (false) {
            System.out.println("date.getTime() = " + date.getTime());
            System.out.println("projectBeginTime = " + projectBeginTime);
            System.out.println("widthPerDay = " + widthPerDay);
            System.out.println("relativeTime = " + relativeTime);
            System.out.println("dayRate = " + dayRate);
        }
        return (int) (xOffset + widthPerDay * dayRate);
    }

    private int calcYAxis(int taskIndex, int height) {
        return datelinePaintingBounds.y + datelinePaintingBounds.height
                + height * taskIndex;
    }

    @Override
    void paintDateLine(Graphics g, int headerHeight, List<Calendar> calendarList) {
        // 描画の準備。
        FontMetrics fontMetrics = g.getFontMetrics();
        g.setColor(Color.BLUE);
        Point paintingBottom = new Point();

        int fontHeight = fontMetrics.getHeight();
        yOffset = headerHeight - fontHeight * 2;
        int x2 = xOffset;
        int y1 = yOffset + fontHeight;
        int y2 = yOffset + fontHeight * 2;
        for (Calendar calendar : calendarList) {
            int x1 = Integer.MAX_VALUE;
            // 2行目。
            for (int i = 0; i < HOURS.length; i++) {
                String hourString = String.format("%02d ", HOURS[i]);
                g.drawString(hourString, x2, y2 - fontHeight / 3);
                int stringWidth = fontMetrics.stringWidth(hourString);
                int rectHeight = fontHeight;
                int rectWidth = stringWidth;
                int rectX = x2 - xOffset;
                int rectY = y2 - rectHeight;
                g.drawRect(rectX, rectY, rectWidth, rectHeight);
                x1 = Math.min(x1, x2);
                x2 += rectWidth;
                paintingBottom.x = Math
                        .max(paintingBottom.x, rectX + rectWidth);
                paintingBottom.y = Math.max(paintingBottom.y, rectY
                        + rectHeight);
            }
            // 1行目。
            DateFormat dateFormat = new SimpleDateFormat("EE dd MM");
            String dateString = dateFormat.format(calendar.getTime());
            g.drawString(dateString, x1, y1 - fontHeight / 3);
            widthPerDay = Math.min(widthPerDay, x2 - x1);
            g.drawRect(x1 - xOffset, yOffset, widthPerDay, fontHeight);

            // paintingBounds を計算する。
            datelinePaintingBounds.x = Math.min(datelinePaintingBounds.x, x1);
            datelinePaintingBounds.y = Math.min(datelinePaintingBounds.y, y1);
        }
        datelinePaintingBounds.width = paintingBottom.x
                - datelinePaintingBounds.x;
        datelinePaintingBounds.height = paintingBottom.y
                - datelinePaintingBounds.y;
    }

    @Override
    void paintTaskBar(Graphics g, int taskIndex, TaskEntity taskEntity) {
        g.setColor(Color.GREEN);
        // 開始日時から終了日時までを描画する。
        Date startDate = taskEntity.getStartDate();
        Date finishDate = taskEntity.getFinishDate();
        int height = getTablePainter().getRowHeight();
        int y = calcYAxis(taskIndex, height);
        int x = calcXAxis(startDate);
        int width = calcXAxis(finishDate) - x;
        // issue #3 TimelineChart タスクの高さをグリッド線よりも低くする。
        g.drawRect(x, y + GAP_BETWEEN_GRID_AND_TASK, width, height
                - GAP_BETWEEN_GRID_AND_TASK * 2);
    }

}
