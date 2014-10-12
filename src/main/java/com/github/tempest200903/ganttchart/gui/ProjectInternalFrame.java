package com.github.tempest200903.ganttchart.gui;

import javax.swing.JInternalFrame;

import com.github.tempest200903.ganttchart.entity.ProjectEntity;

class ProjectInternalFrame extends JInternalFrame {

	private ProjectEntity projectEntity;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectInternalFrame(ProjectEntity projectEntity) {
		super();
		this.projectEntity = projectEntity;
		setTitle(this.projectEntity.getName());
	}

}
