package org.mohammad.gol.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import org.mohammad.app.command.CommandExecutor;
import org.mohammad.gol.component.editor.DrawModeEvent;
import org.mohammad.gol.model.CellState;
import org.mohammad.app.observable.event.EventBus;

public class MainView extends BorderPane {

    private EventBus eventBus;
    private CommandExecutor commandExecutor;
    private SimulationCanvas canvas;

    public MainView(EventBus eventBus, CommandExecutor commandExecutor){
        this.eventBus = eventBus;
        this.commandExecutor = commandExecutor;

        canvas = new SimulationCanvas(eventBus);
        this.setCenter(canvas);

        Toolbar toolbar = new Toolbar(eventBus);
        this.setTop(toolbar);

        this.setOnKeyPressed(this::keyPressedHandle);

    }

    public void addDrawLayer(DrawLayer drawLayer){
        canvas.addDrawLayers(drawLayer);
    }


    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            eventBus.emit(new DrawModeEvent(CellState.ALIVE));
        }else if(event.getCode() == KeyCode.E){
            eventBus.emit(new DrawModeEvent(CellState.DEAD));
        }else if(event.isControlDown() && event.getCode() == KeyCode.Z){
            commandExecutor.undo();
        }
    }

}
