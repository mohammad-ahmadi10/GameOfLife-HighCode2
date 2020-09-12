package org.mohammad.gol.logic.editor;

import org.mohammad.gol.logic.ApplicationState;
import org.mohammad.gol.state.EditorState;
import org.mohammad.gol.utils.CellPostion;

public class Editor {

    private final EditorState editorState;

    private boolean isDrawingEnable = true;

    public Editor(EditorState editorState) {
        this.editorState = editorState;
    }


    public void onAppStateChanged(ApplicationState state){
        switch (state){
            case EDITING -> isDrawingEnable = true;
            case SIMULATING -> isDrawingEnable = false;
        }
    }

    public void handle(CursorEvent event){
        switch (event.getType()){
            case PRESSED -> handleBoardPressed(event.getCellPostion());
            case CUROR_MOVED -> setCursorPos(event.getCellPostion());

        }
    }


    public void handle(DrawModeEvent event){
        CellStateCommand command = new CellStateCommand(event.getCellState());
        command.execute(editorState);
    }

    public void setCursorPos(CellPostion cursorPos){
        EditorCommand command = state ->
                state.getCellPosProperty().setValue(cursorPos);
        command.execute(editorState);
    }

    public void handleBoardPressed(CellPostion cellPos){
        setCursorPos(cellPos);

        if(isDrawingEnable){
            EditorBoardCommand command = new EditorBoardCommand(cellPos, editorState.getCellStateProperty().getValue());
            command.execute(editorState);
        }
    }



}
