package org.mohammad.gol.component.simulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.mohammad.app.command.CommandExecutor;
import org.mohammad.gol.model.Simulation;
import org.mohammad.gol.model.StandardRule;


public class Simulator {

    private final Timeline timeline;
    private Simulation simulation;

    private boolean isPlaying = false;

    private final SimulatorState simulatorState;
    private CommandExecutor commandExecutor;
    private boolean isReset = true;


    public Simulator( SimulatorState simulatorState, CommandExecutor commandExecutor){
        this.simulatorState = simulatorState;
        this.commandExecutor = commandExecutor;
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
        this.simulatorState.getSimulating().setValue(false);
    }

    public void handleStep(){
        if(isReset){
            isReset = false;
            this.simulatorState.getSimulating().setValue(true);
            this.simulation =  new Simulation(simulatorState.getCurBoard().getValue(), new StandardRule());
        }
            this.simulation.step();

            SimulatorCommand command = (state) ->
                    state.getCurBoard().setValue(this.simulation.getBoard());
            commandExecutor.execute(command);
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
