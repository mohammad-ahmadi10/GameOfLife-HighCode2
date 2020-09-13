package org.mohammad.gol.component.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.view.AbstractDrawLayer;

public class BoardDrawLayer extends AbstractDrawLayer {

    private BoardState boardState;

    public BoardDrawLayer(BoardState boardState) {
        this.boardState = boardState;
        this.boardState.getBoard().listenTo(board -> this.invalidate());
    }

    @Override
    public void draw(GraphicsContext gc) {
        Board board = boardState.getBoard().getValue();
        gc.setFill(Color.BLACK);
        for (int x = 0; x <= board.getWidth(); x++) {
            for (int y = 0; y <= board.getHeight(); y++) {
                if(board.getState(x,y) == CellState.ALIVE){
                    gc.fillRect(x,y, 1,1);
                }
            }
        }

    }


    @Override
    public int getLayer() {
        return 0;
    }



}
