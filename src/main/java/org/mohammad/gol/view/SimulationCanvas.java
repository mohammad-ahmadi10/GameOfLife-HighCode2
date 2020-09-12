package org.mohammad.gol.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Scale;
import org.mohammad.gol.InfoBar;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.viewmodel.EditorViewModel;

public class SimulationCanvas extends Pane {

    private Canvas canvas;
    private Affine affine;
    private EditorViewModel editorViewModel;
    private InfoBar infoBar;
    private BoardViewModel boardViewModel;


    public SimulationCanvas(EditorViewModel editorViewModel, InfoBar infoBar, BoardViewModel boardViewModel) {
        this.editorViewModel = editorViewModel;
        this.infoBar = infoBar;
        this.boardViewModel = boardViewModel;

        this.canvas = new Canvas(400,400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoved);


        Scale scale = new Scale(this.canvas.getWidth()/ 10,
                this.canvas.getHeight() / 10);
        this.affine = new Affine(scale);

        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.heightProperty().bind(this.heightProperty());

        this.getChildren().add(this.canvas);
    }

    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        draw(this.boardViewModel.getBoardProperty().getValue());
    }


    private void handleMoved(MouseEvent event) {
        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();

        getCurPos(simX, simY);
    }

    private void getCurPos(int simX, int simY) {
        this.infoBar.setCursorFormat(simX, simY);
    }


    private void handleDraw(MouseEvent event) {

        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();

        this.editorViewModel.handleBoardPressed(simX, simY);
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
            gc.strokeLine(x,0, x, board.getHeight());
        }
        for (int y = 0; y <= board.getHeight(); y++){
            gc.strokeLine(0,y,board.getWidth(), y);
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
