package org.mohammad.gol.component.simulator;

import org.mohammad.gol.model.Board;
import org.mohammad.app.observable.Property;

public class SimulatorState {

    private Property<Board> curBoard = new Property<>();
    private Property<Boolean> simulating  =new Property<>(false);

    public SimulatorState(Board board) {
        curBoard.set(board);
    }


    public Property<Board> getCurBoard() {
        return curBoard;
    }

    public Property<Boolean> getSimulating() {
        return simulating;
    }

}
