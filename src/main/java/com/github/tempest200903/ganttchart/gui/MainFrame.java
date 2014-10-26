package com.github.tempest200903.ganttchart.gui;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;
import com.github.tempest200903.ganttchart.entity.TaskCalendarEntity;
import com.github.tempest200903.ganttchart.entity.TaskConstraintType;
import com.github.tempest200903.ganttchart.entity.TaskEntity;
import com.google.common.collect.Lists;

@SuppressWarnings("unused")
class MainFrame extends JFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit()
			.getScreenSize();

	public MainFrame() throws HeadlessException {
		this("");
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		initialize();
	}

	private JDesktopPane createDesktop() {
		ProjectEntity projectEntity = createProjectEntity();
		ProjectFrame projectFrame = createProjectFrame(projectEntity);
		JDesktopPane desktop = new JDesktopPane();
		desktop.add(projectFrame);
		return desktop;
	}

	private ProjectEntity createProjectEntity() {
		return ProjectEntity.createSampleProjectEntity();
	}

	private ProjectFrame createProjectFrame(ProjectEntity projectEntity) {
		ProjectFrame projectFrame = new ProjectFrame(projectEntity);
		int initialHeight = (int) (SCREEN_SIZE.height * 0.8);
		int initialWidth = (int) (SCREEN_SIZE.width * 0.8);
		projectFrame.setSize(initialWidth, initialHeight);
		projectFrame.setLocation(10, 10);
		projectFrame.setVisible(true);
		projectFrame.setResizable(true);
		projectFrame.setTitle(projectEntity.getName());
		return projectFrame;
	}

	private void initialize() {
		JDesktopPane desktop = createDesktop();
		getContentPane().add(desktop);
	}

}
