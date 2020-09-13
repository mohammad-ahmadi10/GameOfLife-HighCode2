package org.mohammad.gol.state;

import java.util.HashMap;
import java.util.Map;

public class StateRegistry {
    private Map<Class<?>, Object> states = new HashMap<>();

    public  <T> void register(Class<T> stateClass, T state){
        states.put(stateClass, state);
    }

    // wird eine state von type stateClass züruck geben.
    public <T> T getState(Class<T> stateClass){
        return (T) states.get(stateClass);
    }


}
