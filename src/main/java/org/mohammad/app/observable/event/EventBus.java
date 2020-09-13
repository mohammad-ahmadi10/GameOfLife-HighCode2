package org.mohammad.app.observable.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventBus {
    private Map<Class<? extends Event>, List<EventListener>> eventMap = new HashMap<>();


    public void emit(Event event){
        Class eventClass = event.getClass();
        List<EventListener> listeners = eventMap.get(eventClass);
        listeners.forEach(listener -> listener.handle(event));
    }


    public <T extends Event> void addListener(Class<T> eventClass, EventListener<T> listener){
        if(!eventMap.containsKey(eventClass)){
            eventMap.put(eventClass, new LinkedList<>());
        }
        eventMap.get(eventClass).add(listener);

    }




}
