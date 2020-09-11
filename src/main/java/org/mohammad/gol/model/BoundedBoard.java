package org.mohammad.gol.model;

public class BoundedBoard implements Board {

    private int width;
    private int height;

    private CellState[][] board;

    public BoundedBoard(int width, int height) {
        this.width = width;
        this.height = height;
        board = new CellState[width][height];

        for (int y = 0; y < this.width; y++) {
            for (int x = 0; x < this.height; x++) {
                this.setState(x,y,CellState.DEAD);
            }
        }
    }


    @Override
    public BoundedBoard getCopy() {
        BoundedBoard copy = new BoundedBoard(this.width, this.height);

        for (int y = 0; y < this.width; y++) {
            for (int x = 0; x < this.height; x++) {
                copy.setState(x,y, this.getState(x,y));
            }
        }
        return copy;
    }


    @Override
    public void setState(int x, int y, CellState state) {
        if ( y < 0 || y >= height)
            return;
        if (x < 0 || x >= width)
            return;
        this.board[x][y] = state;

    }

    @Override
    public CellState getState(int x, int y) {
        if ( y < 0 || y >= height)
            return CellState.DEAD;
        if (x < 0 || x >= width)
            return CellState.DEAD;
        return this.board[x][y];
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}
