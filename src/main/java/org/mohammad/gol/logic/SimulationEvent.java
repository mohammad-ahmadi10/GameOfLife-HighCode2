package org.mohammad.gol.logic;

import org.mohammad.gol.utils.event.Event;

public class SimulationEvent implements Event {

    public enum Type{
        START,
        STOP,
        STEP,
        REST
    }

    private Type type;

    public SimulationEvent(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
