package org.mohammad.gol.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.mohammad.gol.logic.editor.DrawModeEvent;
import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.event.EventBus;

public class MainView extends BorderPane {

    private EventBus eventBus;

    public MainView(EventBus eventBus){
        this.eventBus = eventBus;
        this.setOnKeyPressed(this::keyPressedHandle);

    }


    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        }else if(event.getCode() == KeyCode.E){
            eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }
    }

}
