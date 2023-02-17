package com.phonepe.SnakesAndLadders.model.boardentity;


import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.exceptions.InvalidLadderPositionException;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

public class Crocodile implements IBoardEntity {

    private EntityName name;
    private Cell head;
    private Cell tail;

    public Crocodile(Cell head, Cell tail){
        this.head = head;
        this.tail = tail;
        this.name = EntityName.CROCODILE;
    }

    @Override
    public void performAction(Player player) {
        System.out.print(" and bitten by snake at " + head.getPosition());
        player.setCell(tail);
    }

    @Override
    public String toString() {
        return "Crocodile{" +
                "name=" + name +
                ", head=" + head.toString() +
                ", tail=" + tail.toString() +
                '}';
    }
}