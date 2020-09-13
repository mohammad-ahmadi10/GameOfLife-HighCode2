package org.mohammad.gol.view;

import org.mohammad.gol.view.DrawLayer;
import org.mohammad.gol.view.InvalidationListener;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDrawLayer implements DrawLayer {

    private List<InvalidationListener> listeners = new LinkedList<>();

    @Override
    public void addInvalidationListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    protected void invalidate(){
        listeners.forEach(InvalidationListener::onInvalided);
    }




}
