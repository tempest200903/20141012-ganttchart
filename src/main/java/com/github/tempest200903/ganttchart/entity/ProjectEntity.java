package com.github.tempest200903.ganttchart.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lombok.Data;
import lombok.NonNull;

import com.google.common.collect.Lists;

/*
 @startuml ProjectEntity.puml.png
 title ClassDiagram ProjectEntity
 package entity {
 class ProjectEntity{
 String name;
 Date startDate;
 }


 class GanttEntity{
 }

 class TaskEntity{
 String name;
 int workTime;
 Date startDate;
 Date endDate;
 TaskConstraintType constraintType;
 Date constraintDate;
 Date deadline;
 TaskCalendarEntity taskCalendar;
 }

 ProjectEntity "1" o- "1..*" GanttEntity
 GanttEntity "1" o- "1..*" TaskEntity

 }
 @enduml
 */

/**
 * プロジェクト。
 * 
 * @author tempest200903
 *
 */
@Data
public class ProjectEntity {

    private static List<GanttEntity> createGanttEntityList() {
        GanttEntity ganttEntity = GanttEntity.createSampleGanttEntity();
        List<GanttEntity> ganttEntityList = Lists.newArrayList(ganttEntity);
        return ganttEntityList;
    }

    public static ProjectEntity createSampleProjectEntity() {
        String name = "project1";
        Date startDate = ProjectEntity.createStartDate();
        List<GanttEntity> ganttEntityList = createGanttEntityList();
        ProjectEntity projectEntity = new ProjectEntity(name, startDate,
                ganttEntityList);
        return projectEntity;
    }

    static Date createStartDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(2014, 2, 3); // 2014年03月03日 月曜日
        Date startDate = calendar.getTime();
        return startDate;
    }

    /**
     * ガント。
     */
    @NonNull
    public List<GanttEntity> ganttEntityList;

    /**
     * 名前。
     */
    @NonNull
    public String name;

    /**
     * 開始日付。
     */
    @NonNull
    public Date startDate;

    public ProjectEntity() {
        super();
    }

    public ProjectEntity(String name, Date startDate,
            List<GanttEntity> ganttEntityList) {
        super();
        this.name = name;
        this.startDate = startDate;
        this.ganttEntityList = ganttEntityList;
        for (GanttEntity ganttEntity : ganttEntityList) {
            ganttEntity.setProjectEntity(this);
        }
    }

    /**
     * DSL でエクスポートする · Issue #8 · tempest200903/20141012-ganttchart
     */
    public void save() {
        ganttEntityList.forEach(GanttEntity::save);
    }

    @Override
    public String toString() {
        // TODO ReflectionToStringBuilder を使う。
        StringBuilder builder = new StringBuilder();
        builder.append("ProjectEntity [name=");
        builder.append(name);
        // builder.append(", startDate=");
        // builder.append(startDate);
        // builder.append(", ganttEntityList=");
        // builder.append(ganttEntityList);
        builder.append("]");
        return builder.toString();
    }

}
