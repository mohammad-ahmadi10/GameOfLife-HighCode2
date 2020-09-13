package org.mohammad.gol.component.editor;

import org.mohammad.app.command.CommandExecutor;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.gol.component.simulator.SimulationEvent;
import org.mohammad.gol.model.CellState;

public class Editor {

    private final EditorState editorState;
    private CommandExecutor commandExecutor;

    private boolean isDrawingEnable = true;

    public Editor(EditorState editorState, CommandExecutor commandExecutor) {

        this.editorState = editorState;
        this.commandExecutor = commandExecutor;
    }


    public void handleSimulatorEvent(SimulationEvent event){
        switch (event.getType()){
            case REST -> isDrawingEnable = true;
            case STEP,START -> isDrawingEnable = false;
        }
    }

    public void handle(CursorEvent event){
        setCursorPos(event.getCellPostion());
        switch (event.getType()){
            case PRESSED -> {
                beginEdit();
                handleEdit(event.getCellPostion());
            }
            case CURSOR_MOVED -> {
                if (editorState.getEditInProgress().get() && isDrawingEnable)
                    handleEdit(event.getCellPostion());
            }
            case RELEASED -> {
                handleEdit(event.getCellPostion());
                endEdit();

            }

        }
    }

    private void beginEdit(){
        editorState.getEditInProgress().set(true);
        editorState.getEdit().set(new Edit());
    }
    private void endEdit() {
        EditorBoardCommand command = new EditorBoardCommand(editorState.getEdit().get());
        commandExecutor.execute(command);
        editorState.getEditInProgress().set(false);
        editorState.getEdit().set(null);
    }


    public void handleEdit(CellPostion cellPos){
        if(isDrawingEnable){
            CellState prevCellState = editorState.getBoardProperty().get().getState(cellPos.getPosX(),cellPos.getPosY());
            CellState newCellState = editorState.getCellStatePro().get();

            Change change = new Change(cellPos, newCellState, prevCellState);
            editorState.getEdit().get().add(change);

        }
    }

    public void handle(DrawModeEvent event){
        CellStateCommand command = new CellStateCommand(event.getCellState());
        commandExecutor.execute(command);
    }

    public void setCursorPos(CellPostion cursorPos){
        EditorCommand command = state ->
                state.getCellPosProperty().set(cursorPos);
        commandExecutor.execute(command);
    }



}
