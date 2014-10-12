package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;

class ProjectFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjectEntity projectEntity;

	public ProjectFrame(ProjectEntity projectEntity) {
		super();
		this.projectEntity = projectEntity;
		setTitle(this.projectEntity.getName());
		initialize();
	}

	private JDesktopPane createDesktop() {
		JDesktopPane desktop = new JDesktopPane();
		desktop.setLayout(new BorderLayout());

		List<GanttEntity> list = this.projectEntity.getGanttEntityList();
		for (GanttEntity ganttEntity : list) {
			GanttFrame ganttFrame = new GanttFrame(ganttEntity);
			ganttFrame.setVisible(true);
			desktop.add(ganttFrame);
		}

		return desktop;
	}

	private void initialize() {
		JDesktopPane desktop = createDesktop();
		getContentPane().add(desktop);

	}

}
