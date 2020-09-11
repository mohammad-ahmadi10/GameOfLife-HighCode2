package org.mohammad.gol.model;

public interface GameRules {
    CellState checkTheState(int x, int y, Board board);
}
