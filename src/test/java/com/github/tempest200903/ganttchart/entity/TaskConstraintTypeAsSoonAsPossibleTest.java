package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TaskConstraintTypeAsSoonAsPossibleTest {

    TaskConstraintTypeAsSoonAsPossible type =
            new TaskConstraintTypeAsSoonAsPossible();

    ProjectEntity projectEntity = ProjectEntity.createSampleProjectEntity();

    @Test
    public void testGetStartDate() {
        TaskEntity taskEntity1 = projectEntity.getGanttEntityList().get(0)
                .getTaskEntityList().get(0);
        Date startDate1 = type.getStartDate(taskEntity1);
        assertThat(startDate1, is(notNullValue()));
    }

    @Test
    public void testGetFinishDate() {
        // type.getFinishDate(taskEntity);
        fail();
    }

}
