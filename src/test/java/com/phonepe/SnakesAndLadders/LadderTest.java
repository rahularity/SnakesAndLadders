package com.phonepe.SnakesAndLadders;

import static org.junit.jupiter.api.Assertions.*;

import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import com.phonepe.SnakesAndLadders.model.boardentity.Ladder;
import org.junit.jupiter.api.Test;

public class LadderTest {

    // Test the creation of the Ladder object with valid input parameters
    @Test
    void testLadderCreation() {
        Cell bottom = new Cell(4);
        Cell top = new Cell(8);
        Ladder ladder = new Ladder(bottom, top);
        assertEquals(EntityName.LADDER, ladder.getName());
        assertEquals(bottom, ladder.getBottom());
        assertEquals(top, ladder.getTop());
    }

    // testing if performAction sets the player to the top cell of the ladder
    @Test
    void testLadderPerformAction() {
        Cell bottom = new Cell(4);
        Cell top = new Cell(8);
        Ladder ladder = new Ladder(bottom, top);
        Player player = new Player("John", bottom, 0, PlayerStatus.PLAYING);
        ladder.performAction(player);
        assertEquals(top, player.getCell());
    }

}
