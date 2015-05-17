package com.github.tempest200903.ganttchart.entity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lombok.Data;
import lombok.NonNull;

/**
 * ガント。
 * 
 * @author tempest200903
 *
 */
@Data
public class GanttEntity implements PropertyChangeListener {

    static Calendar sampleCalendar = Calendar
            .getInstance(TimeZone.getDefault());

    static {
        sampleCalendar.set(2014, 2, 3);
    }

    static Date createSampleFinishDate() {
        sampleCalendar.add(Calendar.DATE, 1);
        return sampleCalendar.getTime();
    }

    public static GanttEntity createSampleGanttEntity() {
        GanttEntity ganttEntity = new GanttEntity();
        TaskEntity[] t = new TaskEntity[8];
        for (int i = 0; i < t.length; i++) {
            t[i] = ganttEntity.createSampleTaskEntity();
            t[i].setName(String.format("task%d", i));
        }
        t[1].addPredecessor(t[0]);
        t[2].addPredecessor(t[0]);
        t[3].addPredecessor(t[2]);
        t[4].addPredecessor(t[2]);
        t[5].addPredecessor(t[4]);
        t[6].addPredecessor(t[4]);
        t[7].addPredecessor(t[6]);

        // デバッグ用。
        if (true) {
            exportGanttEntity(ganttEntity);
        }
        return ganttEntity;
    }

    static Date createSampleStartDate() {
        sampleCalendar.add(Calendar.DATE, 1);
        return sampleCalendar.getTime();
    }

    private static void exportGanttEntity(GanttEntity ganttEntity) {
        System.out.println("begin exportGanttEntity");
        try (OutputStream outputStream = new FileOutputStream("ganttEntity.xml");
                XMLEncoder xmlEncoder = new XMLEncoder(outputStream)) {
            xmlEncoder.writeObject(ganttEntity);
            System.out.println("end exportGanttEntity");
        } catch (IOException e) {
            e.printStackTrace(); // TODO catch
        }
    }

    /** プロパティの変更通知をサポートするオブジェクト */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private ProjectEntity projectEntity;

    /**
     * タスク。
     */
    @NonNull
    private List<TaskEntity> taskEntityList = new ArrayList<>();

    public GanttEntity() {
        super();
    }

    /**
     * リスナの追加を行います.<br/>
     * @param l 追加するリスナ
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    TaskEntity createSampleTaskEntity() {
        String name = "task" + System.currentTimeMillis();
        TaskConstraintType constraintType = new TaskConstraintTypeAsSoonAsPossible();
        TaskCalendarEntity taskCalendar = new TaskCalendarEntity();
        int number = taskEntityList.size() - 1;
        TaskEntity taskEntity = new TaskEntity(number, name, constraintType,
                taskCalendar, 1000L * 60 * 60 * 24, this);
        addTaskEntity(taskEntity);
        return taskEntity;
    }

    void addTaskEntity(TaskEntity taskEntity) {
        taskEntityList.add(taskEntity);
        taskEntity.addPropertyChangeListener(this);
    }

    /**
     * リスナの削除を行います.<br/>
     * @param l 削除するリスナ
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GanttEntity [taskEntityList=");
        builder.append(taskEntityList);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        changes.firePropertyChange(evt);
    }

}
