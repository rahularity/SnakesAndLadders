package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.exceptions.InvalidBoardSizeException;
import com.phonepe.SnakesAndLadders.model.Board;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
import com.phonepe.SnakesAndLadders.model.boardentity.WinnerCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private final int boardSize = 10;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board(boardSize);
    }

    @Test
    public void testBoardCreation() {
        assertNotNull(board.getCells());
        assertEquals(boardSize*boardSize, board.getTotalCells());
        assertEquals(boardSize, board.getSize());
    }

    @Test
    public void testInitBoard() {
        Map<Integer, Cell> cells = board.getCells();
        assertNotNull(cells);
        assertEquals(boardSize*boardSize, cells.size());

        // edge cases to check the cells
        assertTrue(cells.containsKey(1));
        assertTrue(cells.containsKey(boardSize*boardSize));

        // check creation of Winner Entity
        assertTrue(cells.get(boardSize*boardSize).getEntity() instanceof WinnerCell);
    }

    // Test that a board of size 0 or negative cannot be created
    @Test
    public void testInvalidBoardSize() {
        assertThrows(InvalidBoardSizeException.class, () -> new Board(0));
        assertThrows(InvalidBoardSizeException.class, () -> new Board(-1));
    }

    // Test that a board of size 1 has a winner cell at cell 1
    @Test
    public void testBoardSizeOne() {
        Board board = new Board(1);
        Cell cell = board.getCells().get(1);
        IBoardEntity entity = cell.getEntity();
        assertTrue(entity instanceof WinnerCell);
    }
}
