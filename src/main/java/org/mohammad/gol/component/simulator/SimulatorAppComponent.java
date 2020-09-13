package org.mohammad.gol.component.simulator;

import org.mohammad.gol.component.ApplicationComponent;
import org.mohammad.gol.component.ApplicationContext;
import org.mohammad.gol.component.board.BoardState;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;
import org.mohammad.gol.component.editor.EditorState;


public class SimulatorAppComponent implements ApplicationComponent {
    @Override
    public void initComponent(ApplicationContext context) {
        SimulatorState simulatorState = context.getStateRegistry().getState(SimulatorState.class);
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);

        Simulator simulator = new Simulator(simulatorState ,context.getCommandExecutor());
        context.getEventBus().addListener(SimulationEvent.class, simulator::handleType);


        editorState.getBoardProperty().listenTo(simulatorState.getCurBoard()::set);
        simulatorState.getCurBoard().listenTo(simulatorBoard -> {
            if (simulatorState.getSimulating().get())
                boardState.getBoard().set(simulatorBoard);
            });

        }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWith(), context.getBoardHeight());
        SimulatorState simulatorState = new SimulatorState(board);
        context.getStateRegistry().register(SimulatorState.class, simulatorState);
    }
}
