package com.github.tempest200903.ganttchart.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.exparity.hamcrest.date.DateMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class TaskConstraintTypeAsSoonAsPossibleTest {

    TaskConstraintTypeAsSoonAsPossible type =
            new TaskConstraintTypeAsSoonAsPossible();

    ProjectEntity projectEntity = ProjectEntity.createSampleProjectEntity();

    @Test
    public void testGetStartDate() {
        List<TaskEntity> taskEntityList = projectEntity.getGanttEntityList()
                .get(0).getTaskEntityList();
        {
            TaskEntity taskEntity1 = taskEntityList.get(0);
            Date actual = type.getStartDate(taskEntity1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2014, 2, 3);
            Date expected = calendar.getTime();
            assertThat(actual, DateMatchers.sameDay(expected));
        }
        {
            TaskEntity taskEntity2 = taskEntityList.get(1);
            Date actual = type.getStartDate(taskEntity2);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2014, 2, 4);
            Date expected = calendar.getTime();
            assertThat(actual, DateMatchers.sameDay(expected));
        }
    }

    @Test
    public void testGetFinishDate() {
        List<TaskEntity> taskEntityList = projectEntity.getGanttEntityList()
                .get(0).getTaskEntityList();
        {
            TaskEntity taskEntity1 = taskEntityList.get(0);
            Date actual = type.getFinishDate(taskEntity1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2014, 2, 4);
            Date expected = calendar.getTime();
            assertThat(actual, DateMatchers.sameDay(expected));
        }
        {
            TaskEntity taskEntity2 = taskEntityList.get(1);
            Date actual = type.getFinishDate(taskEntity2);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2014, 2, 5);
            Date expected = calendar.getTime();
            assertThat(actual, DateMatchers.sameDay(expected));
        }
    }

}
