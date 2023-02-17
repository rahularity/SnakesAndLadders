package com.phonepe.SnakesAndLadders.model.boardentity;


import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.exceptions.InvalidLadderPositionException;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import lombok.Data;

@Data
public class Ladder implements IBoardEntity {

    private EntityName name;
    private Cell bottom;
    private Cell top;

    public Ladder(Cell bottom, Cell top){

        if (bottom.getPosition() >= top.getPosition()) {
            throw new InvalidLadderPositionException("Invalid ladder position: bottom must be below top");
        }

        this.bottom = bottom;
        this.top = top;
        this.name = EntityName.LADDER;
    }

    @Override
    public void performAction(Player player) {
        System.out.print(" and climed the ladder at " + bottom.getPosition());
        player.setCell(top);
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "name=" + name +
                ", bottom=" + bottom.getPosition() +
                ", top=" + top.getPosition() +
                '}';
    }

}
