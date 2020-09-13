package org.mohammad.gol.viewmodel;

import org.mohammad.gol.model.Board;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.app.observable.Property;

public class BoardViewModel {
    private Board board;

    private Property<Board> boardProperty;

    private Property<CellPostion> CellPosProperty;

    public BoardViewModel(Board board) {
        this.board = board;
        boardProperty = new Property<>(board);
        CellPosProperty = new Property<>();
    }

    public Property<Board> getBoardProperty() {
        return boardProperty;
    }

    public Property<CellPostion> getCellPosProperty() {
        return CellPosProperty;
    }
}
