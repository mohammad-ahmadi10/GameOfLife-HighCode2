package org.mohammad.gol.logic;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.utils.Property;

public class Editor {

    private Property<CellState> cellStateProperty;
    private Property<CellPostion> cellPosProperty;
    private Property<Board> boardProperty;

    private boolean isDrawingEnable = true;

    public Editor(Board board, CellState cellState) {
        cellStateProperty = new Property<>(cellState);
        cellPosProperty = new Property<>();
        this.boardProperty = new Property<>(board);
    }

    public Property<CellState> getCellStateProperty() {
        return cellStateProperty;
    }

    public Property<CellPostion> getCellPosProperty() {
        return cellPosProperty;
    }




    public void onAppStateChanged(ApplicationState state){
        switch (state){
            case EDITING -> {
                isDrawingEnable = true;
                this.boardProperty.setValue(this.boardProperty.getValue());
            }
            case SIMULATING -> {
                isDrawingEnable = false;
            }
        }
    }

    public void handle(CursorEvent event){
        switch (event.getType()){
            case PRESSED -> handleBoardPressed(event.getCellPostion());
            case CUROR_MOVED -> this.getCellPosProperty().setValue(event.getCellPostion());
        }
    }

    public void handle(DrawModeEvent event){
        this.getCellStateProperty().setValue(event.getCellState());
    }

    public void handleBoardPressed(CellPostion cellPos){
        this.getCellPosProperty().setValue(cellPos);
        if(isDrawingEnable){
            Board board = boardProperty.getValue();
            board.setState(cellPos.getPosX(),cellPos.getPosY(), this.getCellStateProperty().getValue());
            this.boardProperty.setValue(board);
        }
    }


    public Property<Board> getBoardProperty() {
        return this.boardProperty;
    }
}
