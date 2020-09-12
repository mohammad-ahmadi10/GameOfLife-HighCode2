package org.mohammad.gol.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.mohammad.gol.logic.AppStateManager;
import org.mohammad.gol.logic.ApplicationState;
import org.mohammad.gol.logic.Editor;
import org.mohammad.gol.logic.SimulationEvent;
import org.mohammad.gol.model.Simulation;
import org.mohammad.gol.model.StandardRule;
import org.mohammad.gol.viewmodel.*;

public class Simulator {

    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private AppStateManager appStateManager;
    private Editor editor;
    private Simulation simulation;
    private boolean isPlaying = false;


    public Simulator(BoardViewModel boardViewModel, AppStateManager appStateManager, Editor editor){
        this.boardViewModel = boardViewModel;
        this.appStateManager = appStateManager;
        this.editor = editor;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> handleStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.simulation =  new Simulation(editor.getBoard(), new StandardRule());
    }

    public void handleType(SimulationEvent event){
        switch (event.getType()){
            case START -> start();
            case STEP ->  handleStep();
            case STOP ->  stop();
            case REST ->  reset();
        }
    }

    private void reset() {
        stop();
        this.simulation =  new Simulation(editor.getBoard(), new StandardRule());
        appStateManager.getAppStateProperty().setValue(ApplicationState.EDITING);
    }

    public void handleStep(){
        if(appStateManager.getAppStateProperty().getValue() != ApplicationState.SIMULATING)
            appStateManager.getAppStateProperty().setValue(ApplicationState.SIMULATING);

            this.simulation.step();
//            this.boardViewModel.getBoardProperty().setValue(this.simulation.getBoard());
            this.boardViewModel.getBoardProperty().setValue(this.simulation.getBoard());
    }



    public void start() {
        if (isPlaying == true)
            return;
        this.timeline.play();
        isPlaying = true;
    }

    public void stop(){
        if(appStateManager.getAppStateProperty().getValue() != ApplicationState.SIMULATING)
            appStateManager.getAppStateProperty().setValue(ApplicationState.SIMULATING);

        this.timeline.stop();
        isPlaying = false;
    }




}
