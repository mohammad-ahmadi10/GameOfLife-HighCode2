package org.mohammad.gol.component.editor;

import org.mohammad.app.observable.CellPostion;
import org.mohammad.gol.model.CellState;

public class Change {
    private final CellPostion cellPostion;
    private final CellState newCellState;
    private final CellState prevCellState;


    public Change(CellPostion cellPostion, CellState newCellState, CellState prevCellState) {
        this.cellPostion = cellPostion;
        this.newCellState = newCellState;
        this.prevCellState = prevCellState;
    }

    public CellPostion getCellPostion() {
        return cellPostion;
    }

    public CellState getNewCellState() {
        return newCellState;
    }

    public CellState getPrevCellState() {
        return prevCellState;
    }
}
