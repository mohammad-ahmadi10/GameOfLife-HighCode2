package org.mohammad.gol.component.board;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.view.AbstractDrawLayer;

public class GridDrawLayer extends AbstractDrawLayer {

    private BoardState boardState;

    public GridDrawLayer(BoardState boardState) {
        this.boardState = boardState;
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        Board board = boardState.getBoard().get();
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.05);
        for (int x = 0; x <= board.getWidth(); x++) {
            gc.strokeLine(x,0, x, board.getHeight());
        }

        for (int y = 0; y <= board.getHeight(); y++){
            gc.strokeLine(0,y,board.getWidth(), y);
        }
    }

    @Override
    public int getLayer() {
        return 10;
    }
}
