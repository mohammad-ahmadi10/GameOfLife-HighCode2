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
        BoardViewModel boardViewModel = new BoardViewModel(initBoard);
        AppStateManager appStateManager = new AppStateManager();

        InfoBarViewModel infoBarViewModel = new InfoBarViewModel();

        Simulator simulator = new Simulator(appStateManager);
        eventBus.addListener(SimulationEvent.class, simulator::handleType);

        simulator.getCurBoard().listenTo(simulationBoard ->
                boardViewModel.getBoardProperty().setValue(simulationBoard)
        );

        Editor editor = new Editor(initBoard, CellState.ALIVE);
        editor.getCellPosProperty().listenTo(cellPos ->
                boardViewModel.getCellPosProperty().setValue(cellPos));

        editor.getBoardProperty().listenTo(editorBoard ->{
            simulator.getInitBoard().setValue(editorBoard);
            boardViewModel.getBoardProperty().setValue(editorBoard);
        });

        editor.getCellPosProperty().listenTo(cellPos ->
                infoBarViewModel.getCellPostionProperty().setValue(cellPos)
        );
        editor.getCellStateProperty().listenTo(cellState ->
                infoBarViewModel.getCellStateProperty().setValue(cellState)
                );


        appStateManager.getAppStateProperty().listenTo(editor::onAppStateChanged);
        eventBus.addListener(CursorEvent.class, editor::handle);
        eventBus.addListener(DrawModeEvent.class, editor::handle);





        MainView mainView = new MainView(eventBus);


        /// ####### View Component #########
        InfoBar infoBar = new InfoBar(infoBarViewModel);
        Toolbar toolbar = new Toolbar(eventBus);


        SimulationCanvas canvas = new SimulationCanvas(boardViewModel, eventBus);
        mainView.setTop(toolbar);
        mainView.setCenter(canvas);
        mainView.setBottom(infoBar);



        boardViewModel.getBoardProperty().listenTo(canvas::draw);
        editor.getCellPosProperty().listenTo(e -> canvas.draw(boardViewModel.getBoardProperty().getValue()));
        editor.getCellPosProperty().listenTo(infoBar::setCursorFormat);



        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}