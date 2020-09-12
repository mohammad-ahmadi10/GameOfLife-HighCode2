package org.mohammad.gol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.mohammad.gol.model.StandardRule;
import org.mohammad.gol.utils.event.Event;
import org.mohammad.gol.viewmodel.*;

public class SimulationViewModel {

    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private AppViewModel appViewModel;
    private EditorViewModel editorViewModel;
    private Simulation simulation;
    private boolean isPlaying = false;


    public SimulationViewModel(BoardViewModel boardViewModel, AppViewModel appViewModel, EditorViewModel editorViewModel){
        this.boardViewModel = boardViewModel;
        this.appViewModel = appViewModel;
        this.editorViewModel = editorViewModel;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> handleStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        this.simulation =  new Simulation(editorViewModel.getBoard(), new StandardRule());
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
        this.simulation =  new Simulation(editorViewModel.getBoard(), new StandardRule());
        appViewModel.getAppStateProperty().setValue(ApplicationState.EDITING);
    }

    public void handleStep(){
        if(appViewModel.getAppStateProperty().getValue() != ApplicationState.SIMULATING)
            appViewModel.getAppStateProperty().setValue(ApplicationState.SIMULATING);

            this.simulation.step();
            this.boardViewModel.getBoardProperty().setValue(this.simulation.getBoard());
    }



    public void start(){
        if(isPlaying == true)
            return;

        this.timeline.play();
        isPlaying = true;
    }

    public void stop(){
        this.timeline.stop();
        isPlaying = false;

    }



}
