package com.github.tempest200903.ganttchart.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.List;

abstract class DateLinePainter {

	public Rectangle getPaintingBounds() {
		return paintingBounds;
	}

	protected Rectangle paintingBounds = new Rectangle();

	abstract void paintDateLine(Graphics g, List<Calendar> calendarList);

}
