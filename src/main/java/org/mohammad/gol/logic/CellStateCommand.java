package org.mohammad.gol.logic;

import org.mohammad.gol.command.EditorCommand;
import org.mohammad.gol.state.EditorState;
import org.mohammad.gol.model.CellState;

public class CellStateCommand implements EditorCommand {

    private CellState newCellState;

    public CellStateCommand(CellState newCellState) {
        this.newCellState = newCellState;
    }


    @Override
    public void execute(EditorState editorState) {
            editorState.getCellStateProperty().setValue(newCellState);
    }
}
