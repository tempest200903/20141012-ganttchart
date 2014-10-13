package com.github.tempest200903.ganttchart.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;
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

	int yOffset = 4;
	int xOffset = 2;

	@Override
	void paintDateLine(Graphics g, List<Calendar> calendarList) {
		FontMetrics fontMetrics = g.getFontMetrics();
		int x2 = 0;
		int height = fontMetrics.getHeight();
		int y1 = height;
		int y2 = height * 2;
		for (Calendar calendar : calendarList) {
			int x1 = Integer.MAX_VALUE;
			// 2行目。
			for (int i = 0; i < HOURS.length; i++) {
				String hourString = String.format("%02d ", HOURS[i]);
				g.drawString(hourString, x2, y2);
				x1 = Math.min(x1, x2);
				int stringWidth = fontMetrics.stringWidth(hourString);
				g.drawRect(x2 - xOffset, y2 - height + yOffset, stringWidth,
						height);
				x2 += stringWidth;
			}
			// 1行目。
			DateFormat dateFormat = new SimpleDateFormat("EE dd MM");
			String dateString = dateFormat.format(calendar.getTime());
			g.drawString(dateString, x1, y1);
			g.drawRect(x1 - xOffset, y1 - height + yOffset, x2 - x1, height);
		}
	}

}
