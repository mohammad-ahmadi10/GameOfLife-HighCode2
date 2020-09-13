module org.mohammad {
    requires javafx.controls;
    exports org.mohammad.gol;
    exports org.mohammad.gol.model;
    exports org.mohammad.gol.viewmodel;
    exports org.mohammad.gol.logic;
    exports org.mohammad.app.command;
    exports org.mohammad.gol.state;
    exports org.mohammad.app.state;
    exports org.mohammad.app.observable;
}