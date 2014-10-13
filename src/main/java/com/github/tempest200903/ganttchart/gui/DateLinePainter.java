package com.github.tempest200903.ganttchart.gui;

import java.awt.Graphics;
import java.util.Calendar;
import java.util.List;

abstract class DateLinePainter {

	abstract void paintDateLine(Graphics g, List<Calendar> calendarList);

}
