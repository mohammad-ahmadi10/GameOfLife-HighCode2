package org.mohammad.gol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;

public class Simulator {

    private Timeline timeline;
    private MainView mainView;
    private AppViewModel appViewModel;

    public Simulator(MainView mainView, AppViewModel appViewModel){
        this.mainView = mainView;
        this.appViewModel = appViewModel;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), this::handleStep));
        timeline.setCycleCount(Timeline.INDEFINITE);

    }

    private void handleStep(ActionEvent event){
        if(this.appViewModel.getAppStateProperty().getValue() == ApplicationState.SIMULATING){
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
