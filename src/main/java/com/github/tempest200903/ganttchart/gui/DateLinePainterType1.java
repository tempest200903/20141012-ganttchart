package com.github.tempest200903.ganttchart.gui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

class DateLinePainterType1 extends DateLinePainter {

	static int[] HOURS = new int[12];

	static {
		for (int i = 0; i < HOURS.length; i++) {
			HOURS[i] = i * 2;
		}
	}

	private int xOffset = 2;

	private int yOffset = Integer.MAX_VALUE;

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
			g.drawRect(x1 - xOffset, yOffset, x2 - x1, fontHeight);

			// paintingBounds を計算する。
			paintingBounds.x = Math.min(paintingBounds.x, x1);
			paintingBounds.y = Math.min(paintingBounds.y, y1);
		}
		paintingBounds.width = paintingBottom.x - paintingBounds.x;
		paintingBounds.height = paintingBottom.y - paintingBounds.y;
	}
}
