package org.mohammad.gol.utils;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {
    private T value;
    private List<propertyListener<T>> propertyListenerList;

    public Property(T value) {
        this.value = value;
        propertyListenerList = new LinkedList<>();
    }

    public Property(){
        this(null);
    }

    public void listenTo(propertyListener<T> propertyListener){
        propertyListenerList.add(propertyListener);
    }

    public void setValue(T value){
            this.value = value;
            notifyAllListener(this.value);
    }

    public Boolean isPresent(){
        return value != null;
    }


    private void notifyAllListener(T value) {
        propertyListenerList.forEach(propertyListener -> propertyListener.handle(value));
    }

    public T getValue() {
        return this.value;
    }


}
