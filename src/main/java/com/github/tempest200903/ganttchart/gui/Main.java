package com.github.tempest200903.ganttchart.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * 
 * @author tempest200903
 *
 */
class Main {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame projectFrame = new MainFrame("ProjectFrame");
		projectFrame.setSize((int) (screenSize.width * 0.8),
				(int) (screenSize.height * 0.8));
		projectFrame.setVisible(true);
	}

}
