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

    static Logger MY_LOGGER = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        MY_LOGGER.debug("Hello World debug");
        MY_LOGGER.trace("Hello World trace");
        MY_LOGGER.info("Hello World info");
        MY_LOGGER.warn("Hello World warn");
        MY_LOGGER.error("Hello World error");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MainFrame mainFrame = new MainFrame("Ganttchart");
        mainFrame.setLocation(10, 10);
        mainFrame.setSize((int) (screenSize.width * 0.7),
                (int) (screenSize.height * 0.7));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}
