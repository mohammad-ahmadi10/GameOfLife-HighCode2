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
import org.mohammad.app.observable.CellPostion;
import org.mohammad.app.observable.event.EventBus;
import org.mohammad.gol.component.editor.CursorEvent;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SimulationCanvas extends Pane {

    private Canvas canvas;
    private Affine affine;
    private EventBus eventBus;

    private List<DrawLayer> drawLayers = new LinkedList<>();

    public SimulationCanvas(EventBus eventBus) {

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

    public void addDrawLayers(DrawLayer drawLayer){
        drawLayers.add(drawLayer);
        drawLayers.sort(Comparator.comparingInt(DrawLayer::getLayer));
        drawLayer.addInvalidationListener(this::draw);
    }



    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        draw();
    }


    private void handleMoved(MouseEvent event) {

            CellPostion cellPos = simCoordinate(event);

            if( (cellPos.getPosX() < 0 || cellPos.getPosX() >= 20 )||
                (cellPos.getPosY() < 0 || cellPos.getPosY() >= 15))
                return;
            eventBus.emit(new CursorEvent(CursorEvent.Type.CURSOR_MOVED, cellPos));

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



    public void draw(){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.setTransform(affine);

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,this.canvas.getWidth(), this.canvas.getHeight());

        drawLayers.forEach(drawLayer -> drawLayer.draw(gc));
    }




}