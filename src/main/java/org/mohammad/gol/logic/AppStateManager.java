package org.mohammad.gol.logic;

import org.mohammad.app.observable.Property;

public class AppStateManager {

    private final Property<ApplicationState> appStateProperty;

    public AppStateManager() {
        appStateProperty = new Property<>();
    }


    public Property<ApplicationState> getAppStateProperty() {
        return appStateProperty;
    }





}
