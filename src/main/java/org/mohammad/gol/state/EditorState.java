package org.mohammad.gol.state;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.app.observable.Property;

public class EditorState {

    private final Property<CellState> cellStateProperty = new Property<>(CellState.ALIVE);
    private final Property<CellPostion> cellPosProperty = new Property<>();
    private final Property<Board> boardProperty = new Property<>();

    public EditorState(Board board) {
        boardProperty.setValue(board);
    }


    public Property<CellState> getCellStateProperty() {
        return cellStateProperty;
    }

    public Property<CellPostion> getCellPosProperty() {
        return cellPosProperty;
    }

    public Property<Board> getBoardProperty() {
        return boardProperty;
    }


}
