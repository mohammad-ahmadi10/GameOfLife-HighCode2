package org.mohammad.app.command;

public interface Command<T> {
    void execute(T t);

    Class<T> getInsClass();
}
