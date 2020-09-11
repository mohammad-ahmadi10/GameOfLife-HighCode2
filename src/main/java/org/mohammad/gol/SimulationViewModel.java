package org.mohammad.gol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;
import org.mohammad.gol.viewmodel.BoardViewModel;

public class SimulationViewModel {

    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private Simulation simulation;

    public SimulationViewModel(BoardViewModel boardViewModel){
        this.boardViewModel = boardViewModel;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> handleStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
//        simulation =  new Simulation(boardViewModel.getBoard(), new StandardRule());

    }


    public void onChangedAppState(ApplicationState state){
        if(state == ApplicationState.SIMULATING)
            simulation =  new Simulation(boardViewModel.getBoard(), new StandardRule());

    }


    public void handleStep(){
            this.simulation.step();
            this.boardViewModel.getBoardProperty().setValue(this.simulation.getBoard());
    }


    public void start(){

            this.timeline.play();
    }

    public void stop(){
        this.timeline.stop();
    }



}
