package org.mohammad.gol;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.viewmodel.EditorViewModel;

public class Toolbar extends ToolBar {

    private SimulationViewModel simulationViewModel;

    private AppViewModel appViewModel;
    private EditorViewModel editorViewModel;

    public Toolbar(SimulationViewModel simulationViewModel, EditorViewModel editorViewModel, AppViewModel appViewModel){
        this.simulationViewModel = simulationViewModel;
        this.editorViewModel = editorViewModel;
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
    private boolean isPlaying = false;

    private void handleStop(ActionEvent event) {
        if(this.simulationViewModel == null)
            return;

            System.out.println("stop pressed");
            isPlaying = false;
            this.simulationViewModel.stop();

    }


    private void handleStart(ActionEvent event) {
        if(isPlaying == true)
            return;
        changeToSimu();
        isPlaying = true;
        this.simulationViewModel.start();
    }




    private void changeToSimu(){
        if(this.appViewModel.getAppStateProperty().getValue() != ApplicationState.SIMULATING){
            this.appViewModel.getAppStateProperty().setValue(ApplicationState.SIMULATING);
        }


    }



    private void handleStep(ActionEvent event) {
        changeToSimu();
        this.simulationViewModel.handleStep();
    }

    private void handleReset(ActionEvent event) {
            this.appViewModel.getAppStateProperty().setValue(ApplicationState.EDITING);
            this.simulationViewModel.stop();
            isPlaying = false;
    }


    private void handleDraw(ActionEvent event) {
            this.editorViewModel.getCellStateProperty().setValue(CellState.ALIVE);
    }


    private void handleErese(ActionEvent event) {
        this.editorViewModel.getCellStateProperty().setValue(CellState.DEAD);
    }



}
