package com.phonepe.SnakesAndLadders;

import static org.junit.jupiter.api.Assertions.*;

import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
import org.junit.jupiter.api.Test;

class CellTest {

    // Test that a newly created cell has no entity
    @Test
    public void testNewCellHasNoEntity() {
        Cell cell = new Cell(1);
        assertNull(cell.getEntity());
    }

    // Test that removing the entity from a cell sets the entity to null
    @Test
    public void testRemoveEntity() {
        Cell cell = new Cell(1);
        IBoardEntity entity = new TestEntity();
        cell.setEntity(entity);
        assertNotNull(cell.getEntity());
        cell.removeEntity();
        assertNull(cell.getEntity());
    }

    // Test that setting the entity of a cell works correctly
    @Test
    public void testSetEntity() {
        Cell cell = new Cell(1);
        IBoardEntity entity1 = new TestEntity();
        IBoardEntity entity2 = new TestEntity();
        cell.setEntity(entity1);
        assertEquals(entity1, cell.getEntity());
        cell.setEntity(entity2);
        assertEquals(entity2, cell.getEntity());
    }

    private class TestEntity implements IBoardEntity {
        // This is a test implementation of IBoardEntity
        // It is not used in the actual implementation of the game
        @Override
        public void performAction(Player player) {

        }
    }

}