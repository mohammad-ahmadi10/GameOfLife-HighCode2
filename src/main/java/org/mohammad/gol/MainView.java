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

public class MainView extends VBox {

    public static final int EDITING = 0;
    public static final int SIMULATING = 1;


    private Canvas canvas;
    private Simulation simulation;
    private Board initBoard;
    private Affine affine;
    private InfoBar infoBar;


    private CellState drawMode = CellState.ALIVE;

    private int applicationState = EDITING;


    public MainView(Board initBoard){
        this.initBoard = initBoard;

        this.setOnKeyPressed(this::keyPressedHandle);

        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);
        this.canvas.setOnMouseMoved(this::handleMoving);


        Scale scale = new Scale(this.canvas.getWidth()/this.initBoard.getWidth(),
                               this.canvas.getHeight() / this.initBoard.getHeight());
        this.affine = new Affine(scale);

        // Padding
        Pane spacer = new Pane();
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Toolbar toolbar = new Toolbar(this);
        infoBar = new InfoBar();
        infoBar.setDrawModeFormat(CellState.ALIVE);
        infoBar.setCursorFormat(0,0);

        this.getChildren().addAll(toolbar, canvas , spacer,  infoBar);
    }


    private void keyPressedHandle(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            setDrawMode(CellState.ALIVE);
        }else if(event.getCode() == KeyCode.E){
            setDrawMode(CellState.DEAD);
        }
    }

    private void handleMoving(MouseEvent event) {
        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();
        this.infoBar.setCursorFormat(simX, simY);
    }

    private void handleDraw(MouseEvent event) {
        if(applicationState == SIMULATING)
            return;

        Point2D simCord = simCoordinate(event);
        int simX = (int) simCord.getX();
        int simY = (int) simCord.getY();

        this.initBoard.setState(simX,simY, drawMode);
        draw();
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

    public void draw(){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setTransform(affine);

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());

        if(this.applicationState == EDITING)
                drawSimulation(this.initBoard);
        else drawSimulation(this.simulation.getBoard());


        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.05);
        for (int x = 0; x <= initBoard.getWidth(); x++) {
            gc.strokeLine(x,0, x, this.canvas.getHeight());
        }
        for (int y = 0; y <= initBoard.getHeight(); y++){
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


    public Simulation getSimulation() {
        return simulation;
    }

    public CellState getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(CellState newDrawMode) {
        this.drawMode = newDrawMode;
        this.infoBar.setDrawModeFormat(newDrawMode);
    }

    public int getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(int newApplicationState) {
        if(newApplicationState == this.applicationState)
            return;

        this.applicationState = newApplicationState;

        if(this.applicationState == SIMULATING)
            this.simulation = new Simulation(this.initBoard, new StandardRule());
    }


}
