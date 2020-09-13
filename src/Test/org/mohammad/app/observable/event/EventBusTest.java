package org.mohammad.app.observable.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventBusTest {

    EventBus eventBus;
    @BeforeEach
    void setUp() {
        eventBus = new EventBus();
    }

    @Test
    void emit_oneListener(){
        Namer namer = new Namer();
        ListenerToNamer listenerToNamer = new ListenerToNamer();
        eventBus.addListener(Namer.class, listenerToNamer);
        eventBus.emit(namer);

        assertTrue(listenerToNamer.hasChanged);
    }

    @Test
    void emit_MultiListener(){
        Namer namer = new Namer();
        ListenerToNamer listenerToNamer = new ListenerToNamer();
        Listener2ToNamer listener2ToNamer = new Listener2ToNamer();

        eventBus.addListener(Namer.class, listenerToNamer);
        eventBus.addListener(Namer.class, listener2ToNamer);

        eventBus.emit(namer);

        assertTrue(listenerToNamer.hasChanged);
        assertTrue(listener2ToNamer.hasChanged);
    }






    public static class Namer implements Event{
        private String name = "sabba";

    }
    public static class ListenerToNamer<T> implements EventListener<Event>{
        private boolean hasChanged = false;

        @Override
        public void handle(Event value) {
            hasChanged = true;
        }
    }

    public static class Listener2ToNamer<T> implements EventListener<Event>{
        private boolean hasChanged = false;

        @Override
        public void handle(Event value) {
            hasChanged = true;
        }
    }







}