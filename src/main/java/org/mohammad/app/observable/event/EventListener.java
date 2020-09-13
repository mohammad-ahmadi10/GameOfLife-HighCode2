package org.mohammad.app.observable.event;

public interface EventListener<T> {
    void handle(T value);

}
