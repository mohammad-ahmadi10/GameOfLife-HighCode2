package org.mohammad.gol.component.board;

import org.mohammad.gol.component.ApplicationComponent;
import org.mohammad.gol.component.ApplicationContext;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;

public class BoardAppComponent implements ApplicationComponent {


    @Override
    public void initComponent(ApplicationContext context) {
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        BoardDrawLayer boardDrawLayer = new BoardDrawLayer(boardState);
        GridDrawLayer gridDrawLayer = new GridDrawLayer(boardState);


        context.getMainView().addDrawLayer(boardDrawLayer);
        context.getMainView().addDrawLayer(gridDrawLayer);

    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWith(), context.getBoardHeight());
        BoardState boardState = new BoardState(board);
        context.getStateRegistry().register(BoardState.class, boardState);

    }
}
