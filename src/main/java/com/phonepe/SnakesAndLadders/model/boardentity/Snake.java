package com.phonepe.SnakesAndLadders.model.boardentity;


import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.exceptions.InvalidSnakePositionException;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

public class Snake implements IBoardEntity {

    private EntityName name;
    private Cell head;
    private Cell tail;

    public Snake(Cell head, Cell tail){
        if (tail.getPosition() >= head.getPosition()) {
            throw new InvalidSnakePositionException("Invalid snake position: head must be upside of tail");
        }
        this.head = head;
        this.tail = tail;
        this.name = EntityName.SNAKE;
    }

    @Override
    public void performAction(Player player) {
        System.out.print(" and bitten by snake at " + head.getPosition());
        player.setCell(tail);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "name=" + name +
                ", head=" + head.getPosition() +
                ", tail=" + tail.getPosition() +
                '}';
    }
}
