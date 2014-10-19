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

 ProjectFrame "1" o- "1" ProjectEntity
 GanttFrame "1" o- "1" GanttEntity
 GanttChart "1" o- "1" GanttEntity
 GanttFrame "1" o- "1" GanttChart

 }
 @enduml
 */

/*
 @startuml GanttChart.puml.png
 title ClassDiagram GanttChart

 package gui {

 GanttChart "1" o- "1"  DateLinePainter
 DateLinePainter <|-- DateLinePainterType1

 }
 @enduml
 */

