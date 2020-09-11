package org.mohammad.gol;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.model.GameRules;

public class StandardRule implements GameRules {


    @Override
    public CellState checkTheState(int x, int y, Board board) {

                int neighbours = countNeighbour(x,y, board);

                if(board.getState(x,y) == CellState.ALIVE){
                    if(neighbours < 2)
                        return CellState.DEAD;
                    else if (neighbours == 2 || neighbours == 3)
                        return CellState.ALIVE;
                    else if(neighbours > 3)
                        return CellState.DEAD;
                }else{
                    if (neighbours == 3)
                        return CellState.ALIVE;
                    else
                        return CellState.DEAD;
                }

            return CellState.DEAD;
    }

    private int countNeighbour(int x, int y, Board board){
        int count =0;

        count += cellCounter(x -1 , y -1,board);
        count += cellCounter(x , y -1,board);
        count += cellCounter(x +1 , y -1,board);

        count += cellCounter(x -1 , y,board);
        count += cellCounter(x +1 , y,board);

        count += cellCounter(x -1 , y +1,board);
        count += cellCounter(x , y +1,board);
        count += cellCounter(x +1 , y +1,board);

        return count;

    }

    private int cellCounter(int x, int y, Board board){
        if(board.getState(x,y) == CellState.ALIVE)
            return 1;
        else return 0;
    }

}
