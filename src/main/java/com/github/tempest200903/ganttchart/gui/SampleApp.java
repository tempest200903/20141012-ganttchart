package com.github.tempest200903.ganttchart.gui;

import java.awt.*;
import javax.swing.*;

public class SampleApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane pane;

	public SampleApp() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(10, 5);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane = new JScrollPane(table);
		this.add(pane, BorderLayout.CENTER);
		this.setSize(new Dimension(300, 200));
	}

	public static void main(String[] args) {
		new SampleApp().setVisible(true);
	}
}
