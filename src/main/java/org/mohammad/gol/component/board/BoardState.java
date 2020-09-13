package org.mohammad.gol.component.board;

import org.mohammad.app.observable.Property;
import org.mohammad.gol.model.Board;

public class BoardState {
    Property<Board> board = new Property<>();

    public BoardState(Board board) {
        this.board.set(board);
    }

    public Property<Board> getBoard() {
        return board;
    }
}
