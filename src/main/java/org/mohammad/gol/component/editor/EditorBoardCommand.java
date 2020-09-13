package org.mohammad.gol.component.editor;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
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
