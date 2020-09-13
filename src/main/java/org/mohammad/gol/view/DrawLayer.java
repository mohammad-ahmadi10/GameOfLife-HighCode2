package org.mohammad.gol.view;

import javafx.scene.canvas.GraphicsContext;

public interface DrawLayer {
    void draw(GraphicsContext gc);

    int getLayer();

    void addInvalidationListener(InvalidationListener listener);


}
