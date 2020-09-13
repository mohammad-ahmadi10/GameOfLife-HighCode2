package org.mohammad.app.command;

import org.mohammad.app.state.StateRegistry;

import java.util.Stack;

public class CommandExecutor {

    private final StateRegistry registry;

    private final Stack<UndoableCommand> undoCommands = new Stack<>();

    public CommandExecutor(StateRegistry registry) {
        this.registry = registry;
    }


    public <T> void execute(Command<T> command){
        T state = registry.getState(command.getInsClass());
        command.execute(state);
    }

    public <T> void execute(UndoableCommand<T> command){
        T state = registry.getState(command.getInsClass());
        undoCommands.push(command);
        command.execute(state);
    }

    public void undo(){
        if(!undoCommands.isEmpty()){
            UndoableCommand command = undoCommands.pop();
            Object state = registry.getState(command.getInsClass());
            command.undo(state);
        }
    }




}
