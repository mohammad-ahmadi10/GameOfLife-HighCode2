package org.mohammad.gol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;
import org.mohammad.gol.viewmodel.BoardViewModel;

public class Simulator {

    private Timeline timeline;
    private BoardViewModel boardViewModel;
    private Simulation simulation;

    public Simulator(BoardViewModel boardViewModel, Simulation simulation){
        this.boardViewModel = boardViewModel;
        this.simulation = simulation;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> handleStep()));
        timeline.setCycleCount(Timeline.INDEFINITE);
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
