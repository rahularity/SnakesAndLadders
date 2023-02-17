package com.phonepe.SnakesAndLadders.model;

import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
import lombok.Data;

@Data
public class Cell {

    private IBoardEntity entity;
    private int position;
    private Player occupiedBy;

    public Cell(int position){
        this.entity = null;
        this.occupiedBy = null;
        this.position = position;
    }

    public void removeEntity(){
        this.entity = null;
    }

}
