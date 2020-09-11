package org.mohammad.gol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.view.SimulationCanvas;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.viewmodel.EditorViewModel;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        Board initBoard = new BoundedBoard(20,15);
        AppViewModel appViewModel = new AppViewModel();
        BoardViewModel boardViewModel = new BoardViewModel(initBoard);
        EditorViewModel editorViewModel = new EditorViewModel(boardViewModel, CellState.ALIVE);
        editorViewModel.setBoard(initBoard);

        SimulationViewModel simulationViewModel = new SimulationViewModel(boardViewModel);
        appViewModel.getAppStateProperty().listenTo(editorViewModel::onAppStateChanged);
        appViewModel.getAppStateProperty().listenTo(simulationViewModel::onChangedAppState);


        MainView mainView = new MainView(editorViewModel);


        /// ####### View Component #########
        InfoBar infoBar = new InfoBar();
        Toolbar toolbar = new Toolbar(simulationViewModel, editorViewModel,appViewModel);
        SimulationCanvas canvas = new SimulationCanvas(editorViewModel, infoBar);
        editorViewModel.getCellStateProperty().listenTo(infoBar::setDrawModeFormat);
        infoBar.setCursorFormat(0,0);
        infoBar.setDrawModeFormat(CellState.ALIVE);


        boardViewModel.getBoardProperty().listenTo(canvas::draw);


        mainView.setTop(toolbar);
        mainView.setCenter(canvas);
        mainView.setBottom(infoBar);



        Scene scene = new Scene(mainView, 1000, 700);
        stage.setScene(scene);
        stage.show();


        boardViewModel.getBoardProperty().setValue(initBoard);
    }

    public static void main(String[] args) {
        launch();
    }

}