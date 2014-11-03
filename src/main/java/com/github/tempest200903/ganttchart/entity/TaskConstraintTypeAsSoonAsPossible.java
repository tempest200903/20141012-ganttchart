package com.github.tempest200903.ganttchart.entity;

import java.util.Date;
import java.util.List;

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
        List<TaskDependencyEntity> predecessorList = taskEntity
                .getPredecessorList();
        if (predecessorList.size() == 0) {
            return taskEntity.getProjectStartDate();
        }
        long maxTime = Long.MIN_VALUE;
        for (TaskDependencyEntity taskDependencyEntity : predecessorList) {
            TaskEntity from = taskDependencyEntity.getFrom();
            Date finishDate = from.getFinishDate();
            maxTime = Math.max(maxTime, finishDate.getTime());
        }
        return new Date(maxTime);
    }

    @Override
    Date getFinishDate(TaskEntity taskEntity) {
        return addDurationToStartDate(taskEntity);
    }

}
