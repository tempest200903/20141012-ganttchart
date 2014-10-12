package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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

	private Date createBeginDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2014, 2, 3);
		Date beginDate = calendar.getTime();
		return beginDate;
	}

	private ProjectEntity createProjectEntity() {
		String name = "project1";
		Date beginDate = createBeginDate();
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
