package org.mohammad.gol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.viewmodel.EditorViewModel;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Board initBoard = new BoundedBoard(10,10);
        AppViewModel appViewModel = new AppViewModel();
        BoardViewModel boardViewModel = new BoardViewModel(initBoard);
        EditorViewModel editorViewModel = new EditorViewModel(boardViewModel, CellState.ALIVE);
        editorViewModel.setBoard(initBoard);
        appViewModel.getAppStateProperty().listenTo(editorViewModel::onAppStateChanged);


        MainView mainView = new MainView(editorViewModel, appViewModel, boardViewModel);

        Scene scene = new Scene(mainView, 640, 480);
        stage.setScene(scene);
        stage.show();


        boardViewModel.getBoardProperty().setValue(initBoard);
    }

    public static void main(String[] args) {
        launch();
    }

}