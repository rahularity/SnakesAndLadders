package com.phonepe.SnakesAndLadders.model.boardentity;


import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;

public class WinnerCell implements IBoardEntity {

    private EntityName name;
    private Cell cell;

    public WinnerCell(Cell cell){
        this.cell = cell;
        this.name = EntityName.WINNER;
    }

    @Override
    public void performAction(Player player) {
        player.setPlayerStatus(PlayerStatus.WON);
        System.out.println(" and reached to the final point " + cell.getPosition() + " and won the Game!");
    }

    @Override
    public String toString() {
        return "WInner{" +
                "name=" + name +
                ", cell=" + cell.getPosition() +
                '}';
    }
}
