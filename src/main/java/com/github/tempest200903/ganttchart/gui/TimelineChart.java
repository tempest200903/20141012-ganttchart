package com.github.tempest200903.ganttchart.gui;

import java.awt.Color;
import java.awt.FontMetrics;
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
	private DateLinePainter dateLinePainter = new DateLinePainterType1();

	@NonNull
	private TablePainter tablePainter;

	TimelineChart(GanttEntity ganttEntity, TablePainter tablePainter) {
		super();
		this.ganttEntity = ganttEntity;
		this.tablePainter = tablePainter;
		assert ganttEntity != null : "ganttEntity";

		System.out.println("this.tablePainter.getRowHeight() =: "
				+ this.tablePainter.getHeaderHeight());
	}

	private List<Calendar> createCalendarList() {
		List<Calendar> dateList = Lists.newArrayList();
		ProjectEntity projectEntity = ganttEntity.getProjectEntity();
		Date beginDate = projectEntity.getBeginDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
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
		dateLinePainter.paintDateLine(g, headerHeight, calendarList);
		return dateLinePainter.getPaintingBounds();
	}

	/**
	 * TaskEntity を描画する。
	 * 
	 * @param g
	 * @param previousPaintingBounds
	 * @param taskEntity
	 * @return 描画領域。
	 */
	private Rectangle paintTaskEntity(Graphics g,
			Rectangle previousPaintingBounds, TaskEntity taskEntity) {
		// 描画の準備。
		FontMetrics fontMetrics = g.getFontMetrics();
		g.setColor(Color.RED);
		int fontHeight = fontMetrics.getHeight();
		Rectangle currentPaintingBounds = new Rectangle();

		// 下線を描画する。
		int x1 = previousPaintingBounds.x;
		int y1 = previousPaintingBounds.y + previousPaintingBounds.height
				+ fontHeight;
		int x2 = previousPaintingBounds.x + previousPaintingBounds.width;
		int y2 = y1;
		g.drawLine(x1, y1, x2, y2);

		// TODO 開始日時から終了日時までを描画する。
		// Date beginDate = taskEntity.getBeginDate();
		// Date endDate = taskEntity.getEndDate();

		// currentPaintingBounds を計算する。
		currentPaintingBounds.x = x1;
		currentPaintingBounds.y = previousPaintingBounds.y
				+ previousPaintingBounds.height;
		currentPaintingBounds.width = x2 - x1;
		currentPaintingBounds.height = y1 - currentPaintingBounds.y;
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
		for (int i = 0; i < taskEntityList.size(); i++) {
			TaskEntity taskEntity = taskEntityList.get(i);
			currentPaintingBounds = paintTaskEntity(g, currentPaintingBounds,
					taskEntity);
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
