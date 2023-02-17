package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.DieStrategies.IDieStrategy;
import com.phonepe.SnakesAndLadders.DieStrategies.MaxStrategy;
import com.phonepe.SnakesAndLadders.PlayerTurnStrategy.IPlayerTurnStrategy;
import com.phonepe.SnakesAndLadders.PlayerTurnStrategy.RoundRobinStrategy;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.exceptions.InvalidPlayerPositionException;
import com.phonepe.SnakesAndLadders.model.Board;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class GameTest {

    Board board = Mockito.mock(Board.class);
    IDieStrategy dieStrategy = Mockito.mock(MaxStrategy.class);
    IPlayerTurnStrategy playerTurnStrategy = Mockito.mock(RoundRobinStrategy.class);

    @Test
    void testAddPlayer() {
        Game game = new Game(dieStrategy, playerTurnStrategy, board);
        Player player = new Player("Rahul", new Cell(2), 0, PlayerStatus.PLAYING);
        when(board.getTotalCells()).thenReturn(100);
        player.getCell().setPosition(1);
        game.addPlayer(player);
        assertTrue(1 == game.getPlayers().size());
        assertEquals(player.toString(), game.getPlayers().get(0).toString());
    }

    @Test
    void testGetWinnersCount() {
        Game game = new Game(dieStrategy, playerTurnStrategy, board);
        Player player1 = new Player("Rahul", new Cell(1), 0, PlayerStatus.PLAYING);
        Player player2 = new Player("Alex", new Cell(1), 0, PlayerStatus.PLAYING);
        when(board.getTotalCells()).thenReturn(100);
        game.addPlayer(player1);
        game.addPlayer(player2);
        player1.setPlayerStatus(PlayerStatus.WON);
        assertEquals(1, game.countWinners());
        player2.setPlayerStatus(PlayerStatus.WON);
        assertEquals(2, game.countWinners());
    }


    // test for moving the player to the new position after getting the roll result
    @Test
    void testMovePlayerToNewPositionAfterRoll() {
        Game game = new Game(dieStrategy, playerTurnStrategy, board);
        Player player = new Player("Rahul", new Cell(1), 0, PlayerStatus.PLAYING);

        Map<Integer, Cell> cells = new HashMap<>();
        cells.put(1, new Cell(1));
        cells.put(2, new Cell(2));
        cells.put(3, new Cell(3));
        cells.put(4, new Cell(4));
        when(board.getCells()).thenReturn(cells);
        when(board.getTotalCells()).thenReturn(100);

        Cell cell1 = board.getCells().get(1);
        player.setCell(cell1);
        cell1.setOccupiedBy(player);

        Cell cell2 = board.getCells().get(4);
        game.movePlayerToNewPositionAfterRoll(player, 3);

        assertEquals(cell2, player.getCell());
        assertNull(cell1.getOccupiedBy());
        assertEquals(player, cell2.getOccupiedBy());
    }

}
