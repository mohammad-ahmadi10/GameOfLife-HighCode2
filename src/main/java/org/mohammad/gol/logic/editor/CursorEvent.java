package org.mohammad.gol.logic.editor;

import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.utils.event.Event;

public class CursorEvent implements Event {

    public enum Type{
        CURSOR_MOVED,
        PRESSED
    }

    private Type type;
    private CellPostion cellPostion;

    public CursorEvent(Type type, CellPostion cellPostion) {
        this.type = type;
        this.cellPostion = cellPostion;
    }


    public Type getType() {
        return type;
    }

    public CellPostion getCellPostion() {
        return cellPostion;
    }


}
