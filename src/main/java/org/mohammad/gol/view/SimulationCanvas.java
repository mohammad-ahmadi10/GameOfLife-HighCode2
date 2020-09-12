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
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.utils.event.EventBus;
import org.mohammad.gol.viewmodel.BoardViewModel;
import org.mohammad.gol.logic.editor.CursorEvent;

public class SimulationCanvas extends Pane {

    private Canvas canvas;
    private Affine affine;
    private BoardViewModel boardViewModel;
    private EventBus eventBus;


    public SimulationCanvas( BoardViewModel boardViewModel,
                            EventBus eventBus) {

        this.boardViewModel = boardViewModel;
        this.eventBus = eventBus;

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

            CellPostion cellPos = simCoordinate(event);

            int width = boardViewModel.getBoardProperty().getValue().getWidth();
            int height = boardViewModel.getBoardProperty().getValue().getHeight();

            if( (cellPos.getPosX() < 0 || cellPos.getPosX() >= width )||
                (cellPos.getPosY() < 0 || cellPos.getPosY() >= height))
                return;

            eventBus.emit(new CursorEvent(CursorEvent.Type.CUROR_MOVED, cellPos));

    }

    private void handleDraw(MouseEvent event) {
        CellPostion cellPos = simCoordinate(event);
        eventBus.emit(new CursorEvent(CursorEvent.Type.PRESSED, cellPos));

    }



    private CellPostion simCoordinate(MouseEvent event){
        double  mouseX = event.getX();
        double  mouseY =  event.getY();

        try {
            Point2D simCord = this.affine.inverseTransform(mouseX, mouseY);

            return new CellPostion((int) simCord.getX(), (int) simCord.getY());

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


        if(boardViewModel.getCellPosProperty().isPresent()){
            gc.setFill(new Color(0.3,0.3,0.3,0.7));
            CellPostion cellPos = boardViewModel.getCellPosProperty().getValue();
            gc.fillRect(cellPos.getPosX(), cellPos.getPosY(), 1,1);
        }



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
        for (int x = 0; x <= board.getWidth(); x++) {
            for (int y = 0; y <= board.getHeight(); y++) {
                if(board.getState(x,y) == CellState.ALIVE){
                    gc.fillRect(x,y, 1,1);
                }
            }
        }
    }



}