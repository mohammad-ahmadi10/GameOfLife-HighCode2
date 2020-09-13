package org.mohammad.gol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.mohammad.app.command.CommandExecutor;
import org.mohammad.app.observable.event.EventBus;
import org.mohammad.app.state.StateRegistry;
import org.mohammad.gol.component.ApplicationComponent;
import org.mohammad.gol.component.ApplicationContext;
import org.mohammad.gol.component.board.BoardAppComponent;
import org.mohammad.gol.component.editor.EditorAppComponent;
import org.mohammad.gol.component.infobar.InfobarComponent;
import org.mohammad.gol.component.simulator.SimulatorAppComponent;
import org.mohammad.gol.view.MainView;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        EventBus eventBus = new EventBus();
        StateRegistry stateRegistry = new StateRegistry();
        CommandExecutor commandExecutor = new CommandExecutor(stateRegistry);
        MainView mainView = new MainView(eventBus, commandExecutor);

        ApplicationContext context =
                new ApplicationContext(eventBus,stateRegistry,commandExecutor,mainView, 20,15);


        List<ApplicationComponent> components = new LinkedList<>();
        components.add(new EditorAppComponent());
        components.add(new SimulatorAppComponent());
        components.add(new BoardAppComponent());
        components.add(new InfobarComponent());

        components.forEach(component -> component.initState(context));
        components.forEach(component -> component.initComponent(context));




        Scene scene = new Scene(mainView, 1200, 800);
        stage.setScene(scene);
        stage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
