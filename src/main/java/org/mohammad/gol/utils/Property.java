package org.mohammad.gol.utils;

import org.mohammad.gol.viewmodel.Listener;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {
    private T value;
    private List<Listener<T>> listenerList;

    public Property(T value) {
        this.value = value;
        listenerList = new LinkedList<>();
    }

    public Property(){
        this(null);
    }

    public void listenTo(Listener<T> listener){
        listenerList.add(listener);
    }

    public void setValue(T value){
            this.value = value;
            notifyAllListener(this.value);
    }

    public Boolean isPresent(){
        return value != null;
    }


    private void notifyAllListener(T value) {
        listenerList.forEach(listener -> listener.handle(value));
    }

    public T getValue() {
        return this.value;
    }


}
