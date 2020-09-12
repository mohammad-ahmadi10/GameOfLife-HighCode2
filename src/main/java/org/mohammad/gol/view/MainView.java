package org.mohammad.gol.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.logic.Editor;

public class MainView extends BorderPane {

    private Editor editor;

    public MainView(Editor editor){
        this.editor = editor;
        this.setOnKeyPressed(this::keyPressedHandle);

    }


    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            this.editor.getCellStateProperty().setValue(CellState.ALIVE);
        }else if(event.getCode() == KeyCode.E){
            this.editor.getCellStateProperty().setValue(CellState.DEAD);
        }
    }

}
