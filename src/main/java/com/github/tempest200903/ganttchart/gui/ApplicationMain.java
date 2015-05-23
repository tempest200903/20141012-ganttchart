package com.github.tempest200903.ganttchart.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author tempest200903
 *
 */
class ApplicationMain {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ApplicationMain.class);
        logger.debug("Hello World debug");
        logger.trace("Hello World trace");
        logger.info("Hello World info");
        logger.warn("Hello World warn");
        logger.error("Hello World error");
        System.exit(0);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MainFrame mainFrame = new MainFrame("ProjectFrame");
        mainFrame.setLocation(10, 10);
        mainFrame.setSize((int) (screenSize.width * 0.6),
                (int) (screenSize.height * 0.6));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
