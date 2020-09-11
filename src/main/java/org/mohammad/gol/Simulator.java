package org.mohammad.gol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Simulator {

    private Timeline timeline;
    private MainView mainView;

    public Simulator(MainView mainView , Simulation simulation){
        this.mainView = mainView;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), this::handleStep));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void handleStep(ActionEvent event){
        if(this.mainView.getApplicationState() == MainView.SIMULATING){
            this.mainView.getSimulation().step();
            this.mainView.draw();
        }

    }

    public void start(){
            this.timeline.play();
    }

    public void stop(){
        this.timeline.stop();
    }



}
