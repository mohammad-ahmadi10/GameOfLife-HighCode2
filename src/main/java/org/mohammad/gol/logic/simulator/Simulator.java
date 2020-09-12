package org.mohammad.gol.logic.simulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.mohammad.gol.logic.AppStateManager;
import org.mohammad.gol.logic.ApplicationState;
import org.mohammad.gol.model.Simulation;
import org.mohammad.gol.model.StandardRule;
import org.mohammad.gol.state.SimulatorState;


public class Simulator {

    private final Timeline timeline;
    private final AppStateManager appStateManager;
    private Simulation simulation;

    private boolean isPlaying = false;

    private final SimulatorState simulatorState;
    private boolean isReset = true;


    public Simulator(AppStateManager appStateManager, SimulatorState simulatorState){
        this.appStateManager = appStateManager;
        this.simulatorState = simulatorState;
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
        isReset = true;
        stop();
        appStateManager.getAppStateProperty().setValue(ApplicationState.EDITING);
    }

    public void handleStep(){
        if(isReset){
            isReset = false;
            appStateManager.getAppStateProperty().setValue(ApplicationState.SIMULATING);
            this.simulation =  new Simulation(simulatorState.getCurBoard().getValue(), new StandardRule());
        }
            this.simulation.step();

            SimulatorStateCommand command = (state) ->
                    state.getCurBoard().setValue(this.simulation.getBoard());
            command.execute(this.simulatorState);
    }



    public void start() {
        if (isPlaying)
            return;
        this.timeline.play();
        isPlaying = true;
    }

    public void stop(){
        this.timeline.stop();
        isPlaying = false;
    }
}
