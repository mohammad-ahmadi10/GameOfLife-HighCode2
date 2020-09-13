package org.mohammad.gol.logic.editor;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.state.EditorState;
import org.mohammad.app.observable.CellPostion;

public class EditorBoardCommand implements EditorCommand {

    private final CellPostion cellPostion;
    private final CellState cellState;

    public EditorBoardCommand(CellPostion cellPostion, CellState cellState) {
        this.cellPostion = cellPostion;
        this.cellState = cellState;
    }

    @Override
    public void execute(EditorState editorState) {
        Board board = editorState.getBoardProperty().getValue();
        board.setState(cellPostion.getPosX(),cellPostion.getPosY(),cellState);
        editorState.getBoardProperty().setValue(board);
    }

}
