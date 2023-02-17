package com.phonepe.SnakesAndLadders.model;

import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.exceptions.InvalidPlayerPositionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
public class Player {

    private String name;
    private Cell cell;
    private int blockedForMoves;
    private PlayerStatus playerStatus;

    public int moveToPosition(int rollResult){
        return cell.getPosition() + rollResult;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", cell=" + cell.getPosition() +
                ", blockedForMoves=" + blockedForMoves +
                ", playerStatus=" + playerStatus +
                '}';
    }
}
