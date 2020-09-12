package org.mohammad.gol.logic;

import org.mohammad.gol.utils.Property;

public class AppStateManager {

    private Property<ApplicationState> appStateProperty;

    public AppStateManager() {
        appStateProperty = new Property<>();
    }


    public Property<ApplicationState> getAppStateProperty() {
        return appStateProperty;
    }





}
