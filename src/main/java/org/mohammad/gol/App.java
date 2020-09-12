package org.mohammad.gol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mohammad.gol.logic.*;
import org.mohammad.gol.logic.editor.CursorEvent;
import org.mohammad.gol.logic.editor.DrawModeEvent;
import org.mohammad.gol.logic.editor.Editor;
import org.mohammad.gol.logic.simulator.SimulationEvent;
import org.mohammad.gol.logic.simulator.Simulator;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;
import org.mohammad.gol.state.EditorState;
import org.mohammad.gol.state.SimulatorState;
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

        SimulatorState simulatorState = new SimulatorState(initBoard);
        Simulator simulator = new Simulator(appStateManager, simulatorState);
        eventBus.addListener(SimulationEvent.class, simulator::handleType);

        simulatorState.getCurBoard().listenTo(simulationBoard ->
                boardViewModel.getBoardProperty().setValue(simulationBoard)
        );

        EditorState editorState = new EditorState(initBoard);

        Editor editor = new Editor(editorState);
        editorState.getCellPosProperty().listenTo(cellPos ->
                boardViewModel.getCellPosProperty().setValue(cellPos));

        editorState.getBoardProperty().listenTo(editorBoard ->{
            simulatorState.getCurBoard().setValue(editorBoard);
            boardViewModel.getBoardProperty().setValue(editorBoard);
        });

        editorState.getCellPosProperty().listenTo(cellPos ->
                infoBarViewModel.getCellPostionProperty().setValue(cellPos)
        );
        editorState.getCellStateProperty().listenTo(cellState ->
                infoBarViewModel.getCellStateProperty().setValue(cellState)
                );


        appStateManager.getAppStateProperty().listenTo(editor::onAppStateChanged);
        appStateManager.getAppStateProperty().listenTo(appState ->{
            if (appState == ApplicationState.EDITING)
                boardViewModel.getBoardProperty().setValue(editorState.getBoardProperty().getValue());
        });



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
        editorState.getCellPosProperty().listenTo(e -> canvas.draw(boardViewModel.getBoardProperty().getValue()));
        editorState.getCellPosProperty().listenTo(infoBar::setCursorFormat);



        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}