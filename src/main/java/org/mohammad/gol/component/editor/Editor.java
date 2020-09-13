package org.mohammad.gol.component.editor;

import org.mohammad.app.command.CommandExecutor;
import org.mohammad.gol.component.simulator.SimulationEvent;
import org.mohammad.app.observable.CellPostion;

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
        switch (event.getType()){
            case PRESSED -> handleBoardPressed(event.getCellPostion());
            case CURSOR_MOVED -> setCursorPos(event.getCellPostion());

        }
    }


    public void handle(DrawModeEvent event){
        CellStateCommand command = new CellStateCommand(event.getCellState());
        commandExecutor.execute(command);
    }

    public void setCursorPos(CellPostion cursorPos){
        EditorCommand command = state ->
                state.getCellPosProperty().setValue(cursorPos);
        commandExecutor.execute(command);
    }

    public void handleBoardPressed(CellPostion cellPos){
        setCursorPos(cellPos);

        if(isDrawingEnable){
            EditorBoardCommand command = new EditorBoardCommand(cellPos, editorState.getCellStateProperty().getValue());
            commandExecutor.execute(command);
        }
    }



}
