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
        EditorState editorState = context.getRegistry().getState(EditorState.class);
        BoardState boardState = context.getRegistry().getState(BoardState.class);
        Editor editor = new Editor(editorState, context.getCommandExecutor());


//
//        editorState.getCellPosProperty().listenTo(cellPos ->
//                infoBarViewModel.getCellPostionProperty().setValue(cellPos)
//        );
//        editorState.getCellStateProperty().listenTo(cellState ->
//                infoBarViewModel.getCellStateProperty().setValue(cellState)
//        );
//
//

        context.getEventBus().addListener(CursorEvent.class, editor::handle);
        context.getEventBus().addListener(DrawModeEvent.class, editor::handle);
        context.getEventBus().addListener(SimulationEvent.class, editor::handleSimulatorEvent);
        context.getEventBus().addListener(SimulationEvent.class, event ->{
            if(event.getType() == SimulationEvent.Type.REST)
                boardState.getBoard().setValue(editorState.getBoardProperty().getValue());
        });
        editorState.getBoardProperty().listenTo(boardState.getBoard()::setValue);



        ToolDrawLayer toolDrawLayer = new ToolDrawLayer(editorState);
        context.getMainView().addDrawLayer(toolDrawLayer);
    }

    @Override
    public void initState(ApplicationContext context) {
        Board board = new BoundedBoard(context.getBoardWith(), context.getBoardHeight());
        EditorState editorState = new EditorState(board);
        context.getRegistry().register(EditorState.class, editorState);

    }
}
