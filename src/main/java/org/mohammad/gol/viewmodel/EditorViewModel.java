package org.mohammad.gol.viewmodel;

import org.mohammad.gol.model.Board;
import org.mohammad.gol.model.CellState;
import org.mohammad.gol.utils.Property;

public class EditorViewModel {
    private Board board;
    private BoardViewModel boardViewModel;
    private Property<CellState> cellStateProperty;

    private boolean isDrawingEnable = true;

    public EditorViewModel(BoardViewModel boardViewModel,CellState cellState) {
        this.boardViewModel = boardViewModel;
        cellStateProperty = new Property<>(cellState);
    }

    public Property<CellState> getCellStateProperty() {
        return cellStateProperty;
    }

    public Board getBoard() {
        return board;
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

    public void handleBoardPressed(int simX ,int simY){
        if(isDrawingEnable){
            this.board.setState(simX,simY, this.getCellStateProperty().getValue());
            this.boardViewModel.getBoardProperty().setValue(this.board);
        }
    }












}
