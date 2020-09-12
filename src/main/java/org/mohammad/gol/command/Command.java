package org.mohammad.gol.command;

public interface Command<T> {
    void execute(T t);

}
