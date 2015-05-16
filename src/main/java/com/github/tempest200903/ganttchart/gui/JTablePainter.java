package com.github.tempest200903.ganttchart.gui;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

class JTablePainter extends TablePainter {

    private TaskTable ganttTable;

    JTablePainter(@Nonnull TaskTable ganttTable1) {
        super();
        Preconditions.checkNotNull("ganttTable1", ganttTable1);
        this.ganttTable = ganttTable1;
    }

    @Override
    int getHeaderHeight() {
        return (int) ganttTable.getTableHeaderSize().getHeight();
    }

    @Override
    int getRowHeight() {
        return ganttTable.getRowHeight();
    }

}
