package org.mohammad.gol.component.editor;

import org.mohammad.app.observable.CellPostion;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;

public class EditorBoardCommand implements UndoableEditorCommand {

    private Edit edit;

    public EditorBoardCommand(Edit edit) {
        this.edit = new Edit(edit);

    }

    @Override
    public void execute(EditorState editorState) {
        Board board = editorState.getBoardProperty().get();
        edit.forEach(change -> {
            CellPostion cellPostion = change.getCellPostion();
            CellState newCellState = change.getNewCellState();

            board.setState(cellPostion.getPosX(),cellPostion.getPosY(),newCellState);
            editorState.getBoardProperty().set(board);
        });


    }

    @Override
    public void undo(EditorState editorState) {
        Board board = editorState.getBoardProperty().get();
        edit.forEach(change -> {
            CellPostion cellPostion = change.getCellPostion();
            CellState prevCellState = change.getPrevCellState();

            board.setState(cellPostion.getPosX(),cellPostion.getPosY(),prevCellState);
            editorState.getBoardProperty().set(board);
        });
    }

}
