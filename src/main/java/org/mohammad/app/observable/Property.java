package org.mohammad.app.observable;

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

    public void set(T value){
            this.value = value;
            notifyAllListener(this.value);
    }

    public Boolean isPresent(){
        return value != null;
    }


    private void notifyAllListener(T value) {
        propertyListenerList.forEach(propertyListener -> propertyListener.handle(value));
    }

    public T get() {
        return this.value;
    }


}
