package org.mohammad.gol.state;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.utils.Property;

public class SimulatorState {

    private Property<Board> curBoard = new Property<>();

    public SimulatorState(Board board) {
        curBoard.setValue(board);
    }


    public Property<Board> getCurBoard() {
        return curBoard;
    }



}
