package org.mohammad.gol.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.Simulation;
import org.mohammad.gol.model.StandardRule;
import org.mohammad.gol.utils.Property;

public class Simulator {

    private Timeline timeline;
    private AppStateManager appStateManager;
    private Simulation simulation;
    private boolean isPlaying = false;

    private Property<Board> initBoard = new Property<>();
    private Property<Board> curBoard = new Property<>();

    public Simulator( AppStateManager appStateManager){
        this.appStateManager = appStateManager;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> handleStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
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
        appStateManager.getAppStateProperty().setValue(ApplicationState.EDITING);
    }

    public void handleStep(){
        if(appStateManager.getAppStateProperty().getValue() != ApplicationState.SIMULATING){
            appStateManager.getAppStateProperty().setValue(ApplicationState.SIMULATING);
            this.simulation =  new Simulation(initBoard.getValue(), new StandardRule());
        }
            this.simulation.step();
            this.curBoard.setValue(this.simulation.getBoard());
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

    public Property<Board> getInitBoard() {
        return initBoard;
    }

    public Property<Board> getCurBoard() {
        return curBoard;
    }
}
