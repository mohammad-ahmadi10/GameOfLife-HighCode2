package org.mohammad.gol.component.editor;

import org.mohammad.gol.component.ApplicationComponent;
import org.mohammad.gol.component.ApplicationContext;
import org.mohammad.gol.component.board.BoardState;
import org.mohammad.gol.component.simulator.SimulationEvent;
import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.BoundedBoard;

public class EditorAppComponent implements ApplicationComponent {
    @Override
    public void initComponent(ApplicationContext context) {
        EditorState editorState = context.getStateRegistry().getState(EditorState.class);
        BoardState boardState = context.getStateRegistry().getState(BoardState.class);
        Editor editor = new Editor(editorState, context.getCommandExecutor());


        context.getEventBus().addListener(CursorEvent.class, editor::handle);
        context.getEventBus().addListener(DrawModeEvent.class, editor::handle);
        context.getEventBus().addListener(SimulationEvent.class, editor::handleSimulatorEvent);
        context.getEventBus().addListener(SimulationEvent.class, event ->{
            if(event.getType() == SimulationEvent.Type.REST)
                boardState.getBoard().set(editorState.getBoardProperty().get());
        });
        editorState.getBoardProperty().listenTo(boardState.getBoard()::set);



        ToolDrawLayer toolDrawLayer = new ToolDrawLayer(editorState);
        context.getMainView().addDrawLayer(toolDrawLayer);

        CurrentEditDrawLayer currentEditDrawLayer = new CurrentEditDrawLayer(editorState);
        context.getMainView().addDrawLayer(currentEditDrawLayer);

    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWith(), context.getBoardHeight());
        EditorState editorState = new EditorState(board);
        context.getStateRegistry().register(EditorState.class, editorState);

    }
}
