package org.mohammad.gol.logic;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.CellPostion;
import org.mohammad.gol.utils.Property;
import org.mohammad.gol.viewmodel.BoardViewModel;

public class Editor {
    private Board board;
    private BoardViewModel boardViewModel;
    private Property<CellState> cellStateProperty;
    private Property<CellPostion> cellPosProperty;


    private boolean isDrawingEnable = true;

    public Editor(BoardViewModel boardViewModel, CellState cellState) {
        this.boardViewModel = boardViewModel;
        cellStateProperty = new Property<>(cellState);
        cellPosProperty = new Property<>();
    }

    public Property<CellState> getCellStateProperty() {
        return cellStateProperty;
    }

    public Property<CellPostion> getCellPosProperty() {
        return cellPosProperty;
    }


    public void setBoard(Board board) {
        this.board = board;
    }


    public void onAppStateChanged(ApplicationState state){
        switch (state){
            case EDITING -> {
                isDrawingEnable = true;
                this.boardViewModel.getBoardProperty().setValue(this.board);
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
            this.board.setState(cellPos.getPosX(),cellPos.getPosY(), this.getCellStateProperty().getValue());
//            this.boardViewModel.getBoardProperty().setValue(this.board);
        }
    }


    public Board getBoard() {
        return this.board;
    }
}
