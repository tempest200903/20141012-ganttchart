package com.github.tempest200903.ganttchart.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class GanttTableTest {

    private static Date createStartDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(2014, 2, 3, 0, 0); // 2014年03月03日 月曜日 00:00
        Date startDate = calendar.getTime();
        return startDate;
    }

    @Test
    public void testToDurationString() {
        String actual = TaskTable.toDurationString(86400000L);
        String expected = "1.00 days";
        assertThat(actual, is(expected));
    }

    @Test
    public void testToStartDateString() {
        Date startDate = createStartDate();
        String actual = TaskTable.toDateString(startDate);
        String expected = "14/03/03 00:00";
        assertThat(actual, is(expected));
    }

}
