package org.mohammad.gol;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;

public class Toolbar extends ToolBar {

    private Simulator simulator;

    private MainView mainView;
    private AppViewModel appViewModel;

    public Toolbar(MainView mainView, AppViewModel appViewModel){
        this.mainView = mainView;
        this.appViewModel = appViewModel;

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
        System.out.println("stop pressed");
        this.simulator.stop();
    }

    private void handleStart(ActionEvent event) {
        changeToSimu();
        this.simulator.start();
    }

    private void changeToSimu(){
        this.appViewModel.getAppStateProperty().setValue(ApplicationState.SIMULATING);
        this.simulator = new Simulator(mainView, appViewModel);
    }

    private void handleReset(ActionEvent event) {
            this.appViewModel.getAppStateProperty().setValue(ApplicationState.EDITING);
            this.simulator = null;
            this.mainView.draw();
    }

    private void handleDraw(ActionEvent event) {
            this.mainView.setDrawMode(CellState.ALIVE.ALIVE);
    }

    private void handleErese(ActionEvent event) {
            this.mainView.setDrawMode(CellState.DEAD);
    }

    private void handleStep(ActionEvent event) {
        changeToSimu();
        this.mainView.getSimulation().step();
        this.mainView.draw();
    }

}