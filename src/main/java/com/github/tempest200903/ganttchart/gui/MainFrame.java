package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;

class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainFrame() throws HeadlessException {
		this("");
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		initialize();
	}

	private ProjectEntity createProjectEntity() {
		String name = "project1";
		ZonedDateTime beginDate = ZonedDateTime.of(2014, 1, 1, 0, 0, 0, 0,
				ZoneId.of("Asia/Tokyo"));
		GanttEntity ganttEntity = new GanttEntity();
		ProjectEntity projectEntity = new ProjectEntity(name, beginDate,
				ganttEntity);
		return projectEntity;
	}

	private void initialize() {
		ProjectEntity projectEntity = createProjectEntity();

		ProjectInternalFrame iframe = new ProjectInternalFrame(projectEntity);
		iframe.setSize(600, 400);
		iframe.setLocation(10, 10);
		iframe.setVisible(true);
		iframe.setResizable(true);
		iframe.getContentPane().setLayout(new BorderLayout());

		JDesktopPane desktop = new JDesktopPane();
		desktop.add(iframe);
		getContentPane().add(desktop);
	}

}
