package org.mohammad.gol.component.infobar;

import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.app.observable.Property;

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
