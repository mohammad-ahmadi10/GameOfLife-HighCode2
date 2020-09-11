package org.mohammad.gol;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.model.GameRules;

public class Simulation {

    private Board board;
    private GameRules rules;

    public Simulation(Board board, GameRules rules) {
        this.board = board;
        this.rules = rules;
    }

    public void step(){
        Board newBoard =  this.board.getCopy();

        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                CellState state = rules.checkTheState(x,y, this.board);
                newBoard.setState(x,y , state);
            }
        }

        this.board = newBoard;
    }


    public Board getBoard() {
        return board;
    }
}
