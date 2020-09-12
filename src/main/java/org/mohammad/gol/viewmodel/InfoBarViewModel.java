package org.mohammad.gol.viewmodel;

import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.utils.Property;

public class InfoBarViewModel {
    Property<CellPostion> cellPostionProperty = new Property<>();
    Property<CellState> cellStateProperty = new Property<>();


    public Property<CellPostion> getCellPostionProperty() {
        return cellPostionProperty;
    }

    public Property<CellState> getCellStateProperty() {
        return cellStateProperty;
    }
}
