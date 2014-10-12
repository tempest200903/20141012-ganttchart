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

	private Date createBeginDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2014, 2, 3);
		Date beginDate = calendar.getTime();
		return beginDate;
	}

	private JDesktopPane createDesktop() {
		ProjectFrame projectFrame = createProjectFrame();
		JDesktopPane desktop = new JDesktopPane();
		desktop.add(projectFrame);
		return desktop;
	}

	private GanttEntity createGanttEntity() {
		List<TaskEntity> taskEntityList = Lists.newArrayList(
				createTaskEntity(), createTaskEntity(), createTaskEntity(),
				createTaskEntity(), createTaskEntity(), createTaskEntity(),
				createTaskEntity(), createTaskEntity());
		GanttEntity ganttEntity = new GanttEntity(taskEntityList);
		return ganttEntity;
	}

	private List<GanttEntity> createGanttEntityList() {
		GanttEntity ganttEntity = createGanttEntity();
		List<GanttEntity> ganttEntityList = Lists.newArrayList(ganttEntity);
		return ganttEntityList;
	}

	private ProjectEntity createProjectEntity() {
		String name = "project1";
		Date beginDate = createBeginDate();
		List<GanttEntity> ganttEntityList = createGanttEntityList();
		ProjectEntity projectEntity = new ProjectEntity(name, beginDate,
				ganttEntityList);
		return projectEntity;
	}

	private ProjectFrame createProjectFrame() {
		ProjectEntity projectEntity = createProjectEntity();
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

	private TaskEntity createTaskEntity() {
		String name = "task" + System.currentTimeMillis();
		int workTime = 1;
		Date beginDate = createBeginDate();
		Date endDate = createBeginDate();
		TaskConstraintType constraintType = TaskConstraintType.AS_SOON_AS_POSSIBLE;
		TaskCalendarEntity taskCalendar = new TaskCalendarEntity();

		TaskEntity taskEntity = new TaskEntity(name, workTime, beginDate,
				endDate, constraintType, taskCalendar);
		return taskEntity;
	}

	private void initialize() {
		JDesktopPane desktop = createDesktop();
		getContentPane().add(desktop);
	}

}
