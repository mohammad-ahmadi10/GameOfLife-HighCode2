package org.mohammad.gol.viewmodel;

import org.mohammad.gol.utils.Property;

public class AppViewModel {

    private Property<ApplicationState> appStateProperty;

    public AppViewModel(ApplicationState state) {
        appStateProperty = new Property<>(state);
    }


    public Property<ApplicationState> getAppStateProperty() {
        return appStateProperty;
    }





}
