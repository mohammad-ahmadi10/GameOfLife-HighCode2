package org.mohammad.gol.component.editor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.mohammad.app.observable.CellPostion;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.view.AbstractDrawLayer;

public class CurrentEditDrawLayer extends AbstractDrawLayer {
    private EditorState editorState;

    public CurrentEditDrawLayer(EditorState editorState) {
        this.editorState = editorState;
        this.editorState.getEdit().listenTo(edit -> this.invalidate());
    }


    @Override
    public void draw(GraphicsContext gc) {
        if(!editorState.getEdit().isPresent())
            return;


        editorState.getEdit().get().forEach(change ->{
            CellPostion cellPostion = change.getCellPostion();
            CellState cellState = change.getNewCellState();
            if(cellState == CellState.ALIVE)
                 gc.setFill(Color.BLACK);
            else gc.setFill(Color.LIGHTGRAY);

            gc.fillRect(cellPostion.getPosX(),cellPostion.getPosY(), 1,1);
        });
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
