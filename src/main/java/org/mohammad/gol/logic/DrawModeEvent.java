package org.mohammad.gol.logic;

import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.event.Event;

public class DrawModeEvent implements Event {
    private CellState cellState;

    public DrawModeEvent(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getCellState() {
        return cellState;
    }
}
