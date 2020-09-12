package org.mohammad.gol.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.mohammad.gol.logic.DrawModeEvent;
import org.mohammad.gol.logic.SimulationEvent;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.event.EventBus;

public class Toolbar extends ToolBar {


    private EventBus eventBus;

    public Toolbar(EventBus eventBus){
        this.eventBus = eventBus;

        Button drawBtn = new Button("Draw");
        Button eraseBtn = new Button("Erase");
        Button stepBtn = new Button("Step");
        Button resetBtn = new Button("Reset");
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");



        drawBtn.setOnAction(this::handleDraw);
        eraseBtn.setOnAction(this::handleErese);
        stepBtn.setOnAction(this::handleStep);
        resetBtn.setOnAction(this::handleReset);
        startBtn.setOnAction(this::handleStart);
        stopBtn.setOnAction(this::handleStop);
        this.getItems().setAll(drawBtn, eraseBtn,resetBtn, stepBtn, startBtn, stopBtn);
    }

    private void handleStop(ActionEvent event) {
        eventBus.emit(new SimulationEvent(SimulationEvent.Type.STOP));
    }


    private void handleStart(ActionEvent event) {
        eventBus.emit(new SimulationEvent(SimulationEvent.Type.START));
    }


    private void handleStep(ActionEvent event) {
        eventBus.emit(new SimulationEvent(SimulationEvent.Type.STEP));
    }

    private void handleReset(ActionEvent event) {
        eventBus.emit(new SimulationEvent(SimulationEvent.Type.REST));
    }


    private void handleDraw(ActionEvent event) {
            eventBus.emit(new DrawModeEvent(CellState.ALIVE));
    }

    private void handleErese(ActionEvent event) {
        eventBus.emit(new DrawModeEvent(CellState.DEAD));
    }

}
