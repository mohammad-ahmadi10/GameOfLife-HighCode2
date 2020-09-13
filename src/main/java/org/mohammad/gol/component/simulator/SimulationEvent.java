package org.mohammad.gol.component.simulator;

import org.mohammad.app.observable.event.Event;

public class SimulationEvent implements Event {

    public enum Type{
        START,
        STOP,
        STEP,
        REST
    }

    private final Type type;

    public SimulationEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
