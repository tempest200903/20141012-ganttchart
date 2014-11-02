package com.github.tempest200903.ganttchart.entity;

import java.util.Date;

/**
 * できるだけ早く
 * 
 * @author tempest200903
 *
 */
class TaskConstraintTypeAsSoonAsPossible extends TaskConstraintType {

    private static final TaskConstraintTypeAsSoonAsPossible INSTANCE =
            new TaskConstraintTypeAsSoonAsPossible();

    static TaskConstraintTypeAsSoonAsPossible getInstance() {
        return INSTANCE;
    }

    @Override
    Date getStartDate(TaskEntity taskEntity) {
        return taskEntity.getProjectStartDate();
    }

    @Override
    Date getFinishDate(TaskEntity taskEntity) {
        return addDurationToStartDate(taskEntity);
    }

}
