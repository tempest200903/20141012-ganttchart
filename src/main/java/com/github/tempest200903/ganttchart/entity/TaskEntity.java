package com.github.tempest200903.ganttchart.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * タスク。
 * 
 * @author tempest200903
 *
 */
public class TaskEntity {

    /**
     * プロパティの変更を通知する仕組み。
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * 制約の指定日。
     */
    @Getter
    private Date constraintDate;

    /**
     * 制約の種類。
     */
    @Getter
    @NonNull
    private TaskConstraintType constraintType;

    /**
     * 期限。
     */
    @Getter
    private Date deadline;

    /**
     * 期間。単位はミリ秒。
     */
    @Getter
    private long duration;

    /**
     * 終了日付。
     */
    // private Date finishDate;

    @Getter
    @NonNull
    private GanttEntity ganttEntity;

    /**
     * 名前。
     */
    @Getter
    @Setter
    @NonNull
    private String name;

    /**
     * 番号。
     */
    @Getter
    private int number;

    /**
     * 先行タスク。
     */
    @Getter
    private List<TaskDependencyEntity> predecessorList = new ArrayList<>();

    /**
     * 後続タスク。
     */
    @Getter
    private List<TaskDependencyEntity> successorList = new ArrayList<>();

    /**
     * タスクカレンダー。
     */
    @Getter
    @NonNull
    private TaskCalendarEntity taskCalendar;

    /**
     * 作業時間。
     */
    private int workTime;

    public TaskEntity() {
    }

    /**
     * 開始日付。
     */
    // private Date startDate;

    TaskEntity(int number, String name, TaskConstraintType constraintType,
            TaskCalendarEntity taskCalendar, long duration,
            GanttEntity ganttEntity) {
        super();
        this.number = number;
        this.name = name;
        this.constraintType = constraintType;
        this.taskCalendar = taskCalendar;
        this.duration = duration;
        this.ganttEntity = ganttEntity;
    }

    void addPredecessor(TaskEntity taskEntity) {
        TaskDependencyEntity taskDependencyEntity = new TaskDependencyEntity(
                taskEntity, this);
        predecessorList.add(taskDependencyEntity);
        taskEntity.successorList.add(taskDependencyEntity);
    }

    /**
     * リスナの追加を行います.<br/>
     * @param l 追加するリスナ
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public Date getFinishDate() {
        Date date = constraintType.getFinishDate(this);
        assert date != null;
        return date;
    }

    Date getProjectStartDate() {
        return getGanttEntity().getProjectEntity().getStartDate();
    }

    public Date getStartDate() {
        Date date = constraintType.getStartDate(this);
        assert date != null;
        return date;
    }

    /**
     * リスナの削除を行います.<br/>
     * @param l 削除するリスナ
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void setDuration(long newValue) {
        Object oldValue = duration;
        duration = newValue;
        changes.firePropertyChange("duration", oldValue, newValue);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TaskEntity [constraintDate=");
        builder.append(constraintDate);
        builder.append(", constraintType=");
        builder.append(constraintType);
        builder.append(", deadline=");
        builder.append(deadline);
        builder.append(", duration=");
        builder.append(duration);
        builder.append(", name=");
        builder.append(name);
        builder.append(", predecessorList=");
        builder.append(predecessorList);
        builder.append(", successorList=");
        builder.append(successorList);
        builder.append(", taskCalendar=");
        builder.append(taskCalendar);
        builder.append(", workTime=");
        builder.append(workTime);
        builder.append("]");
        return builder.toString();
    }

}
