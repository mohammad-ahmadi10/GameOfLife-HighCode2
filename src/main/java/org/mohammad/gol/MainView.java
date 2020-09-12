package org.mohammad.gol;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.EditorViewModel;

public class MainView extends BorderPane {

    private EditorViewModel editorViewModel;

    public MainView(EditorViewModel editorViewModel){
        this.editorViewModel = editorViewModel;
        this.setOnKeyPressed(this::keyPressedHandle);

    }


    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            this.editorViewModel.getCellStateProperty().setValue(CellState.ALIVE);
        }else if(event.getCode() == KeyCode.E){
            this.editorViewModel.getCellStateProperty().setValue(CellState.DEAD);
        }
    }

}
