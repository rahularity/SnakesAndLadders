package com.phonepe.SnakesAndLadders.PlayerTurnStrategy;

import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Player;

import java.util.List;

public class RoundRobinStrategy implements IPlayerTurnStrategy{

    private List<Player> players;
    private int currentPlayerIndex = 0;

    public RoundRobinStrategy(List<Player> players){
        this.players = players;
    }

    @Override
    public Player nextPlayer() {


        Player nextPlayer = players.get(currentPlayerIndex);

        while(nextPlayer.getBlockedForMoves() > 0 || nextPlayer.getPlayerStatus() == PlayerStatus.WON) {
            // if the playing is blocked for playing the next turn
            if(nextPlayer.getBlockedForMoves() > 0) {
                nextPlayer.setBlockedForMoves(nextPlayer.getBlockedForMoves()-1);
            }

            updateNextPlayerIndex();
            nextPlayer = players.get(currentPlayerIndex);
        }

        updateNextPlayerIndex();
        return nextPlayer;
    }

    private void updateNextPlayerIndex(){
        if(currentPlayerIndex+1 == players.size()){
            currentPlayerIndex = 0;
        }else{
            ++currentPlayerIndex;
        }
    }

    public int incrementPlayer(){
        return 0;
    }
}
