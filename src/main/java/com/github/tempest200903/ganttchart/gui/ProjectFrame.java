package com.github.tempest200903.ganttchart.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.TextAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tempest200903.ganttchart.entity.GanttEntity;
import com.github.tempest200903.ganttchart.entity.ProjectEntity;

class ProjectFrame extends JInternalFrame {

    static Logger MY_LOGGER = LoggerFactory.getLogger(ProjectFrame.class);

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ProjectEntity projectEntity;

    private TextAction saveAction = new TextAction("save") {
        /** . */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
            MY_LOGGER
                    .debug("saveAction.new TextAction() {...}.actionPerformed(ActionEvent) "
                            + e.toString());
            projectEntity.save();
        }
    };

    public ProjectFrame(ProjectEntity projectEntity) {
        super();
        this.projectEntity = projectEntity;
        initializeContainer();
    }

    private JDesktopPane createDesktop() {
        JDesktopPane desktop = new JDesktopPane();
        desktop.setLayout(new BorderLayout());

        List<GanttEntity> list = this.projectEntity.getGanttEntityList();
        for (GanttEntity ganttEntity : list) {
            GanttFrame ganttFrame = new GanttFrame(ganttEntity);
            ganttFrame.setResizable(true);
            ganttFrame.setVisible(true);
            desktop.add(ganttFrame);
        }

        return desktop;
    }

    private JMenuBar createMenuBar() {
        // DSL でエクスポートする · Issue #8 · tempest200903/20141012-ganttchart
        JMenuBar menuBar = new JMenuBar();
        JMenuItem saveMenu = new JMenuItem(saveAction);
        menuBar.add(saveMenu);
        return menuBar;
    }

    private void initializeContainer() {
        setTitle(this.projectEntity.getName());
        JDesktopPane desktop = createDesktop();
        getContentPane().add(desktop);

        // DSL でエクスポートする · Issue #8 · tempest200903/20141012-ganttchart
        JMenuBar menubar = createMenuBar();
        setJMenuBar(menubar);
    }

}
