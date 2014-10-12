package com.github.tempest200903.ganttchart.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
 @startuml ApplicationMain.puml.png
 title ClassDiagram ApplicationMain
 package entity {
 class ProjectEntity{
 }
 class GanttEntity{
 }
 ProjectEntity "1" o- "1..*"  GanttEntity
 GanttEntity "1" o- "1..*" TaskEntity
 }

 package gui {

 ApplicationMain "1" o- "1" MainFrame
 MainFrame "1" o- "1" ProjectFrame
 ProjectFrame "1" o- "1..*" GanttFrame

 ProjectFrame "1" o- "1" ProjectEntity
 GanttFrame "1" o- "1" GanttEntity

 }
 @enduml
 */

/**
 * 
 * @author tempest200903
 *
 */
class ApplicationMain {

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame mainFrame = new MainFrame("ProjectFrame");
		mainFrame.setLocation(20, 20);
		mainFrame.setSize((int) (screenSize.width * 0.9),
				(int) (screenSize.height * 0.9));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
