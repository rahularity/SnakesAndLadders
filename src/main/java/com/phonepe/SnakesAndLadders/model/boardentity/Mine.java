package com.phonepe.SnakesAndLadders.model.boardentity;


import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

public class Mine implements IBoardEntity {

    private EntityName name;
    private Cell position;

    public Mine(Cell position){
        this.position = position;
        this.name = EntityName.MINE;
    }

    @Override
    public void performAction(Player player) {
        System.out.print(" and stepped onto a mine at " + position.getPosition());
        player.setBlockedForMoves(2);
        System.out.print(" and got himself blacked for next 2 turns.");
    }

    @Override
    public String toString() {
        return "Mine{" +
                "name=" + name +
                ", position=" + position.getPosition() +
                '}';
    }
}
