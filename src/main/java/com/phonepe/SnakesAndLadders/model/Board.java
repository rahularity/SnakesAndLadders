package com.phonepe.SnakesAndLadders.model;

import com.phonepe.SnakesAndLadders.exceptions.InvalidBoardSizeException;
import com.phonepe.SnakesAndLadders.model.boardentity.WinnerCell;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Data
public class Board {

    private int size;
    private int totalCells;
    private Map<Integer, Cell> cells;

    public Board(int size) {

        if (size <= 0) {
            throw new InvalidBoardSizeException("Board size must be greater than 0");
        }

        this.size = size;
        this.totalCells = size*size;
        cells = new HashMap<>();
        initBoard();
    }

    private void initBoard() {
        for(int i=1 ; i <= size*size ; i++){
            cells.put(i, new Cell(i));
        }

        WinnerCell winnerCell = new WinnerCell(cells.get(totalCells));
        cells.get(totalCells).setEntity(winnerCell);
    }

}
