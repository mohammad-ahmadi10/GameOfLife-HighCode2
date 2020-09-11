package org.mohammad.gol.model;

public interface Board {
    Board getCopy();
    void setState(int x, int y, CellState state);
    CellState getState(int x, int y);


    int getWidth();
    int getHeight();
}
