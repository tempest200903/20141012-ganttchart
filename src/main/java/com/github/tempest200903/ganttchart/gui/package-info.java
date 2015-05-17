package com.github.tempest200903.ganttchart.gui;

/*
@startuml gui.puml.png
title ClassDiagram gui

package entity {

    class ProjectEntity{
    }
    class GanttEntity{
    }
    ProjectEntity "1" o- "1..*"  GanttEntity
    GanttEntity "1" o- "1..*" TaskEntity

}

package gui {

    ApplicationMain "1" o- "1" MainFrame
    MainFrame "1" o- "1" ProjectFrame
    ProjectFrame "1" o- "1..*" GanttFrame
    ProjectFrame "1" o- "1..*" NetworkFrame
   
    ProjectFrame "1" o- "1" ProjectEntity
    GanttFrame "1" o- "1" GanttEntity
    TaskTable "1" o- "1" GanttEntity
    TimelineChart "1" o- "1" GanttEntity
    GanttFrame "1" o- "1" TimelineChart
    GanttFrame "1" o- "1" TaskTable
    TaskTable "1" o- "1" JTable

}
@enduml
 */

/*
@startuml TimelineChart.puml.png
title ClassDiagram TimelineChart

package gui {

    TimelineChart "1" o- "1"  TimelinePainter
    TimelinePainter <|-- TimelinePainterType1

}
@enduml
 */

/*
https://github.com/tempest200903/20141012-ganttchart/issues/4
predecessor を変更したら TimelineChart を再描画する。 · Issue #4 · tempest200903/20141012-ganttchart
@startuml{plantuml_seq_sample.png}
title シーケンス図

activate JTable
JTable -> TaskTableTableModelListener : tableChanged

activate TaskTableTableModelListener
TaskTableTableModelListener -> TaskEntity : setDuration

activate TaskEntity
TaskEntity -> GanttEntity : propertyChange

activate GanttEntity 
GanttEntity -> TimelineChart : propertyChange

activate TimelineChart  
TimelineChart -> TimelineChart : repaint

@enduml
*/
