package org.mohammad.gol.component.editor;

import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.event.Event;

public class DrawModeEvent implements Event {
    private CellState cellState;

    public DrawModeEvent(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }
}
