package org.mohammad.gol.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.viewmodel.InfoBarViewModel;

public class InfoBar extends HBox {

    private Label drawModeLbl;
    private Label cursorLbl;

    public InfoBar(InfoBarViewModel infoBarViewModel){
        infoBarViewModel.getCellStateProperty().listenTo(this::setDrawModeFormat);
        infoBarViewModel.getCellPostionProperty().listenTo(this::setCursorFormat);

        drawModeLbl = new Label();
        cursorLbl = new Label();

        // Padding
        Pane spacer = new Pane();
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        setDrawModeFormat();
        setCursorFormat();

        this.getChildren().addAll(drawModeLbl,spacer,  cursorLbl);
    }



    public void setDrawModeFormat(CellState drawMode) {
        String drawModeStr ;
        if (drawMode == CellState.ALIVE)
            drawModeStr = "drawing";
        else drawModeStr = "erasing";

        this.drawModeLbl.setText(String.format("DrawMode: %s" , drawModeStr));
    }

    private void setDrawModeFormat(){
        setDrawModeFormat(CellState.ALIVE);
    }


    public void setCursorFormat(CellPostion cellPos)
    {
        this.cursorLbl.setText(String.format("Cursor: (%d , %d)" , cellPos.getPosX()+1, cellPos.getPosY()+1));
    }
    private void setCursorFormat(){
        this.cursorLbl.setText(String.format("Cursor: (%d , %d)" , 1 ,1));
    }

}
