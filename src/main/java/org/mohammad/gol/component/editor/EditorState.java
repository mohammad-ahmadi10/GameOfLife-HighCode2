package org.mohammad.gol.component.editor;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.app.observable.Property;

public class EditorState {

    private final Property<CellState> cellStateProperty = new Property<>(CellState.ALIVE);
    private final Property<CellPostion> cellPosProperty = new Property<>();
    private final Property<Board> boardProperty = new Property<>();

    private final Property<Boolean> editInProgress = new Property<>(false);
    private Property<Edit> edit = new Property<>();
    public EditorState(Board board) {
        boardProperty.set(board);
    }

    public Property<CellState> getCellStatePro() {
        return cellStateProperty;
    }

    public Property<CellPostion> getCellPosProperty() {
        return cellPosProperty;
    }

    public Property<Board> getBoardProperty() {
        return boardProperty;
    }

    public Property<Boolean> getEditInProgress() {
        return editInProgress;
    }

    public Property<Edit> getEdit() {
        return edit;
    }
}
