package org.mohammad.gol.command;

import org.mohammad.gol.state.StateRegistry;

public class CommandExecutor {

    private final StateRegistry registry;

    public CommandExecutor(StateRegistry registry) {
        this.registry = registry;
    }


    public <T> void execute(Command<T> command){
        T state = registry.getState(command.getInsClass());
        command.execute(state);
    }

}
