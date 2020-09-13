package org.mohammad.gol.component;

import org.mohammad.app.command.CommandExecutor;
import org.mohammad.app.observable.event.EventBus;
import org.mohammad.app.state.StateRegistry;
import org.mohammad.gol.view.MainView;

// is like a Object that contains other objects, and
// provide other Objects to others

public class ApplicationContext {
    //implementing base component
    private EventBus eventBus;
    private StateRegistry registry;
    private CommandExecutor commandExecutor;

    private MainView mainView;

    private int boardWith;
    private int boardHeight;

    public ApplicationContext(EventBus eventBus, StateRegistry registry, CommandExecutor commandExecutor, MainView mainView, int boardWith, int boardHeight) {
        this.eventBus = eventBus;
        this.registry = registry;
        this.commandExecutor = commandExecutor;
        this.mainView = mainView;
        this.boardWith = boardWith;
        this.boardHeight = boardHeight;
    }


    public EventBus getEventBus() {
        return eventBus;
    }

    public StateRegistry getRegistry() {
        return registry;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public MainView getMainView() {
        return mainView;
    }

    public int getBoardWith() {
        return boardWith;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
