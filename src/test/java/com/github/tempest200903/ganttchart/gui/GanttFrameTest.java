package com.github.tempest200903.ganttchart.gui;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

public class GanttFrameTest {

    @Test
    public void testToDurationString() {
        String actual = GanttFrame.toDurationString(86400000L);
        String expected = "1.00 days";
        assertThat(actual, is(expected));
    }

}
