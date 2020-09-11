package org.mohammad.gol;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Scale;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.AppViewModel;
import org.mohammad.gol.viewmodel.ApplicationState;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.viewmodel.EditorViewModel;

public class MainView extends VBox {



    private Canvas canvas;
    private EditorViewModel editorViewModel;
    private BoardViewModel boardViewModel;
    private Affine affine;
    private InfoBar infoBar;



    public MainView(SimulationViewModel simulationViewModel,EditorViewModel editorViewModel,AppViewModel appViewModel, BoardViewModel boardViewModel){
        this.editorViewModel = editorViewModel;
        this.boardViewModel = boardViewModel;
        this.boardViewModel.getBoardProperty().listenTo(this::onChangedBoard);
        this.setOnKeyPressed(this::keyPressedHandle);


        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoving);


        Scale scale = new Scale(this.canvas.getWidth()/this.editorViewModel.getBoard().getWidth(),
                               this.canvas.getHeight() / this.editorViewModel.getBoard().getHeight());
        this.affine = new Affine(scale);

        // Padding
        Pane spacer = new Pane();
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Toolbar toolbar = new Toolbar(simulationViewModel, editorViewModel,appViewModel);
        infoBar = new InfoBar(editorViewModel);
        infoBar.setCursorFormat(0,0);

        this.getChildren().addAll(toolbar, canvas , spacer,  infoBar);
    }

    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            this.editorViewModel.getCellStateProperty().setValue(CellState.ALIVE);
        }else if(event.getCode() == KeyCode.E){
            this.editorViewModel.getCellStateProperty().setValue(CellState.DEAD);
        }
    }

    private void handleMoving(MouseEvent event) {
        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();
        this.infoBar.setCursorFormat(simX, simY);
    }

    private void handleDraw(MouseEvent event) {

        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();

        this.editorViewModel.handleBoardPressed(simX, simY);
    }


    private void onChangedBoard(Board board) {
        draw(board);
    }


    private Point2D simCoordinate(MouseEvent event){
        double  mouseX = event.getX();
        double  mouseY =  event.getY();

        try {
            Point2D simCord = this.affine.inverseTransform(mouseX, mouseY);

            return simCord;

        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException("Non invertable transform");
        }
    }


    public void draw(Board board){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setTransform(affine);

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());

        this.drawSimulation(board);


        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.05);
        for (int x = 0; x <= board.getWidth(); x++) {
            gc.strokeLine(x,0, x, this.canvas.getHeight());
        }
        for (int y = 0; y <= board.getHeight(); y++){
            gc.strokeLine(0,y,this.canvas.getWidth(), y);
        }
    }


    private void drawSimulation(Board board){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if(board.getState(x,y) == CellState.ALIVE){
                    gc.fillRect(x,y, 1,1);
                }
            }
        }
    }
}
