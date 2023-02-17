package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.boardentity.Snake;
import org.junit.Before;
import org.junit.Test;
import com.phonepe.SnakesAndLadders.exceptions.InvalidSnakePositionException;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SnakeTest {

    private Snake snake;

    @Before
    public void setUp() {
        Cell head = new Cell(25);
        Cell tail = new Cell(10);
        snake = new Snake(head, tail);
    }

    @Test(expected = InvalidSnakePositionException.class)
    public void testConstructor_invalidPosition() {
        Cell head = new Cell(10);
        Cell tail = new Cell(20);
        Snake invalidSnake = new Snake(head, tail);
    }

    @Test
    public void testPerformAction() {
        Cell playerCell = new Cell(25);
        Player player = new Player("Alice", playerCell, 0, PlayerStatus.PLAYING);
        snake.performAction(player);
        assertEquals(player.getCell().getPosition(), 10);
    }

    @Test
    public void testToString() {
        assertEquals(snake.toString(), "Snake{name=SNAKE, head=25, tail=10}");
    }
}
