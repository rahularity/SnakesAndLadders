package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.DieStrategies.IDieStrategy;
import com.phonepe.SnakesAndLadders.PlayerTurnStrategy.IPlayerTurnStrategy;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.exceptions.InvalidPlayerPositionException;
import com.phonepe.SnakesAndLadders.exceptions.InvalidRollResultException;
import com.phonepe.SnakesAndLadders.exceptions.NoPlayerException;
import com.phonepe.SnakesAndLadders.model.Board;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
import com.phonepe.SnakesAndLadders.model.boardentity.WinnerCell;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {

    private Board board;
    private IDieStrategy dieStrategy;
    private IPlayerTurnStrategy playerTurnStrategy;

    public Game(IDieStrategy dieStrategy, IPlayerTurnStrategy playerTurnStrategy, Board board){

        if (dieStrategy == null || playerTurnStrategy == null || board == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        this.board = board;
        this.dieStrategy = dieStrategy;
        this.playerTurnStrategy = playerTurnStrategy;
    }

    public void play(){

        try{

            if(board.getPlayers().size() == 0){
                throw new NoPlayerException("There should be at least one player to play the game.");
            }

            // play until winning condition has been met
            while(!winningCondition()) {

                // Game is deciding which player is next
                Player player = playerTurnStrategy.nextPlayer();

                // Game is rolling the dice for this player
                int rollResult = dieStrategy.roll();
                System.out.print(player.getName() + " rolled a " + rollResult);

                if(rollResult <= 0){
                    throw new InvalidRollResultException("The result of the roll of dice is zero or negative.");
                }

                // Game will move the player according to the roll result
                movePlayerToNewPositionAfterRoll(player, rollResult);
            }
            System.out.println("GAME OVER!!");

        } catch (Exception e){
            System.out.println("An error occurred during the game: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private boolean winningCondition() {
        return countWinners() == board.getPlayers().size()-1;
    }

    protected void movePlayerToNewPositionAfterRoll(Player player, int rollResult) {
        Cell playerCurrentCell = player.getCell();

        if(playerCurrentCell.getPosition() + rollResult > board.getTotalCells()){
            System.out.println(" and player cannot be moved. He requires " + (board.getTotalCells() - playerCurrentCell.getPosition()) + " or less to move or to win.");
            return;
        }

        // when the player make a move set the previous cell unoccupied
        playerCurrentCell.setOccupiedBy(null);

        Cell playerFinalCell =  board.getCell(playerCurrentCell.getPosition() + rollResult);
        // set cell for the player
        player.setCell(playerFinalCell);

        // check for existence of any entity on the final cell, if exists then perform entity action
        while(playerFinalCell.getEntity() != null && !(playerFinalCell.getEntity() instanceof WinnerCell)) {
            IBoardEntity entity = playerFinalCell.getEntity();
            entity.performAction(player);
            playerFinalCell = player.getCell();
        }

        if(playerFinalCell.getEntity() != null && playerFinalCell.getEntity().getClass() == WinnerCell.class){
            WinnerCell entity = (WinnerCell) playerFinalCell.getEntity();
            entity.performAction(player);
            return;
        }

        System.out.println(" and moved from " + playerCurrentCell.getPosition() + " to " + playerFinalCell.getPosition());

        // if the cell is already occupied then the player has to start from the beginning
        if(playerFinalCell.getOccupiedBy() != null){
            Player occupiedByPlayer = playerFinalCell.getOccupiedBy();
            occupiedByPlayer.setCell(board.getCell(1));
            board.getCell(1).setOccupiedBy(occupiedByPlayer);
            System.out.println("The cell " + playerFinalCell.getPosition() + " was already occupied by " + occupiedByPlayer.getName() + " hence " + occupiedByPlayer.getName() + " has to move to the starting of the board " + occupiedByPlayer.getCell().getPosition());
        }
        playerFinalCell.setOccupiedBy(player);

    }

    int countWinners() {
        return board.getPlayers().stream().mapToInt(x -> x.getPlayerStatus() == PlayerStatus.WON ? 1 : 0).sum();
    }

}
