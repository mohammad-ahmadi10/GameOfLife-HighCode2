package org.mohammad.gol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mohammad.gol.logic.*;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.event.EventBus;
import org.mohammad.gol.view.*;
import org.mohammad.gol.viewmodel.*;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();

        Board initBoard = new BoundedBoard(20,15);
        AppStateManager appStateManager = new AppStateManager();

        BoardViewModel boardViewModel = new BoardViewModel(initBoard);
        Editor editor = new Editor(boardViewModel, CellState.ALIVE);
        editor.setBoard(initBoard);
        appStateManager.getAppStateProperty().listenTo(editor::onAppStateChanged);
        eventBus.addListener(CursorEvent.class, editor::handle);
        eventBus.addListener(DrawModeEvent.class, editor::handle);


        Simulator simulator = new Simulator(boardViewModel, appStateManager, editor);
        eventBus.addListener(SimulationEvent.class, simulator::handleType);



        MainView mainView = new MainView(editor);


        /// ####### View Component #########
        InfoBar infoBar = new InfoBar();
        Toolbar toolbar = new Toolbar(eventBus);
        SimulationCanvas canvas = new SimulationCanvas(editor, boardViewModel, eventBus);
        mainView.setTop(toolbar);
        mainView.setCenter(canvas);
        mainView.setBottom(infoBar);



        editor.getCellStateProperty().listenTo(infoBar::setDrawModeFormat);
        boardViewModel.getBoardProperty().listenTo(canvas::draw);
        editor.getCellPosProperty().listenTo(e -> canvas.draw(boardViewModel.getBoard()));
        editor.getCellPosProperty().listenTo(infoBar::setCursorFormat);


        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();

//        boardViewModel.getBoardProperty().setValue(initBoard);
    }

    public static void main(String[] args) {
        launch();
    }

}