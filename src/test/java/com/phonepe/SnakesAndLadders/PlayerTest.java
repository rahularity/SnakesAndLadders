package com.phonepe.SnakesAndLadders;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

class PlayerTest {

    // Test that a player can be moved to a new position
    @Test
    public void testMoveToPosition() {
        Cell cell = new Cell(1);
        Player player = new Player("Test Player", cell, 0, PlayerStatus.PLAYING);
        int newPosition = player.moveToPosition(5);
        assertEquals(6, newPosition);
    }


}
