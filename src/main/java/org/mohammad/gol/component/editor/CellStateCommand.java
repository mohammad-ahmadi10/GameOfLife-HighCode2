package org.mohammad.gol.component.editor;

import org.mohammad.gol.model.CellState;

public class CellStateCommand implements EditorCommand {

    private final CellState newCellState;

    public CellStateCommand(CellState newCellState) {
        this.newCellState = newCellState;
    }


    @Override
    public void execute(EditorState editorState) {
            editorState.getCellStateProperty().setValue(newCellState);
    }
}
