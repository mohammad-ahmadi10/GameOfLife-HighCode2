package org.mohammad.gol;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.viewmodel.EditorViewModel;

public class InfoBar extends HBox {

    private Label drawModeLbl;
    private Label cursorLbl;
    private EditorViewModel editorViewModel;

    public InfoBar(EditorViewModel editorViewModel){
        this.editorViewModel = editorViewModel;
        this.editorViewModel.getCellStateProperty().listenTo(this::setDrawModeFormat);
        drawModeLbl = new Label();
        cursorLbl = new Label();

        // Padding
        Pane spacer = new Pane();
        spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(drawModeLbl,spacer,  cursorLbl);
    }



    public void setDrawModeFormat(CellState drawMode) {
        String drawModeStr ;
        if (drawMode == CellState.ALIVE)
            drawModeStr = "drawing";
        else drawModeStr = "erasing";

        this.drawModeLbl.setText(String.format("DrawMode: %s" , drawModeStr));
    }

    public void setCursorFormat(int x, int y){
        this.cursorLbl.setText(String.format("Cursor: (%d , %d)" , x ,y));
    }
}
