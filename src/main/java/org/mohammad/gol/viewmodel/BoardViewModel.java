package org.mohammad.gol.viewmodel;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.utils.Property;

public class BoardViewModel {
    private Board board;

    private Property<Board> boardProperty;

    public BoardViewModel(Board board) {
        this.board = board;
        boardProperty = new Property<>(board);
    }

    public Board getBoard() {
        return board;
    }

    public Property<Board> getBoardProperty() {
        return boardProperty;
    }
}
