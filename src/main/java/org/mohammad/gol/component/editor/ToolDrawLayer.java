package org.mohammad.gol.component.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.gol.view.AbstractDrawLayer;

public class ToolDrawLayer extends AbstractDrawLayer {

    private EditorState editorState;

    public ToolDrawLayer(EditorState editorState) {
        this.editorState = editorState;
        this.editorState.getCellPosProperty().listenTo(cp -> this.invalidate());
    }


    @Override
    public void draw(GraphicsContext gc) {
        CellPostion cellPos = editorState.getCellPosProperty().getValue();
        if(cellPos != null){
            gc.setFill(new Color(0.3,0.3,0.3,0.7));
            gc.fillRect(cellPos.getPosX(), cellPos.getPosY(), 1,1);
        }

    }

    @Override
    public int getLayer() {
        return 9;
    }
}
