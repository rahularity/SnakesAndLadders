package com.phonepe.SnakesAndLadders.model;

import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.exceptions.InvalidBoardSizeException;
import com.phonepe.SnakesAndLadders.exceptions.InvalidEntityTypeException;
import com.phonepe.SnakesAndLadders.exceptions.InvalidPlayerPositionException;
import com.phonepe.SnakesAndLadders.model.boardentity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Board {

    private int size;
    private int totalCells;
    private Map<Integer, Cell> cells;
    private List<Player> players;

    public Board(int size) {

        if (size <= 0) {
            throw new InvalidBoardSizeException("Board size must be greater than 0");
        }

        this.size = size;
        this.totalCells = size*size;
        cells = new HashMap<>();
        players = new ArrayList<>();
        initBoard();
    }

    private void initBoard() {
        for(int i=1 ; i <= size*size ; i++){
            cells.put(i, new Cell(i));
        }
        this.setEntity(EntityName.WINNER, cells.size());
    }

    public void setEntity(EntityName entityName, Integer first, Integer second) {

        // consider cell1 as the home of the entity
        Cell cell1 = getCell(first);
        Cell cell2 = getCell(second);

        switch (entityName) {
            case SNAKE -> cell1.setEntity(new Snake(cell1, cell2));
            case LADDER -> cell1.setEntity(new Ladder(cell1, cell2));
            default -> throw new InvalidEntityTypeException("This type of entity is not allowed");
        }

    }

    public void setEntity(EntityName entityName, Integer first) {

        // this cell is the home of the entity
        Cell cell = getCell(first);

        switch (entityName) {
            case CROCODILE:
                Crocodile crocodile = new Crocodile(cell);
                Cell tail = getCell(first-5);
                crocodile.setTail(tail);
                cell.setEntity(crocodile);
                break;
            case MINE:
                cell.setEntity(new Mine(cell));
                break;
            case WINNER:
                cell.setEntity(new WinnerCell(cell));
                break;
            default:
                throw new InvalidEntityTypeException("This type of entity is not allowed");
        }

    }

    public IBoardEntity getEntity(Integer position){
        return getCell(position).getEntity();
    }

    public void addPlayer(Integer playerPosition, String playerName){
        try {
            if(!isValidPlayerPosition(playerPosition)) {
                throw new InvalidPlayerPositionException("Player: " + playerName + " - cannot be at an invalid position");
            }
            Player player = new Player(playerName, getCell(playerPosition), 0, PlayerStatus.PLAYING);
            getCell(playerPosition).setOccupiedBy(player);
            this.players.add(player);

        } catch (InvalidPlayerPositionException e) {
            // Log the exception and ignore the player
            System.out.println("Invalid player position for player " + playerName + ". Ignoring player.");
            e.printStackTrace();
        }
    }

    private boolean isValidPlayerPosition(int position) {
        return position > 0 && position < this.getTotalCells();
    }

    public Cell getCell(Integer position){
        return this.getCells().get(position);
    }
}
