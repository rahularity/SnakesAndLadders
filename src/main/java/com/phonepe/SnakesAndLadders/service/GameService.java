package com.phonepe.SnakesAndLadders.service;

import com.phonepe.SnakesAndLadders.DieStrategies.MaxStrategy;
import com.phonepe.SnakesAndLadders.Game;
import com.phonepe.SnakesAndLadders.PlayerTurnStrategy.RoundRobinStrategy;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Board;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
import com.phonepe.SnakesAndLadders.model.boardentity.Ladder;
import com.phonepe.SnakesAndLadders.model.boardentity.Snake;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GameService {

    public static void main(String[] args) throws IOException {

        Board board = new Board(10);
        List<IBoardEntity> boardEntityList = new ArrayList<>();
        List<Player> playersList = new ArrayList<>();

        Resource resource = new ClassPathResource("SnakeAndLadderInput");
        File file = resource.getFile();
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            // input snakes
            int totalSnakes = Integer.parseInt(br.readLine());
            while(totalSnakes-- != 0){
                String line = br.readLine();
                List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                // Adding the entity to the cell and hence adding them to the board
                Cell snakeHead = board.getCells().get(positions.get(0));
                Cell snakeTail = board.getCells().get(positions.get(1));
                Snake snake = new Snake(snakeHead, snakeTail);
                snakeHead.setEntity(snake);

                boardEntityList.add(snake);
            }

            int totalLadders = Integer.parseInt(br.readLine());
            while(totalLadders-- != 0){
                String line = br.readLine();
                List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                // Adding the entity to the cell and hence adding them to the board
                Cell ladderBottom = board.getCells().get(positions.get(0));
                Cell ladderTop = board.getCells().get(positions.get(1));
                Ladder ladder = new Ladder(ladderBottom, ladderTop);
                ladderBottom.setEntity(ladder);

                boardEntityList.add(ladder);
            }

            int totalPlayers = Integer.parseInt(br.readLine());
            while(totalPlayers-- != 0){
                String line = br.readLine();
                List<String> details = Arrays.stream(line.split(" ")).map(x -> x.toString()).toList();

                String playerName = details.get(0);
                Integer playerPosition = Integer.parseInt(details.get(1));

                // Adding the player to the cell and hence adding them to the board
                Cell cell = board.getCells().get(playerPosition);
                Player player = new Player(playerName, cell, 0, PlayerStatus.PLAYING);
                cell.setOccupiedBy(player);

                playersList.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(IBoardEntity boardEntity : boardEntityList){
            System.out.println(boardEntity.toString());
        }
        for(Player player : playersList){
            System.out.println(player);
        }

        // GAME SETUP
        MaxStrategy maxDieStrategy = new MaxStrategy();
        maxDieStrategy.setTotalDies(1);
        RoundRobinStrategy roundRobinPlayerTurnStrategy = new RoundRobinStrategy(playersList);
        Game game = new Game(maxDieStrategy, roundRobinPlayerTurnStrategy, board);
        for(Player player : playersList){
            game.addPlayer(player);
        }

        // Play the game
        game.play();


        // Get the location of the player who lost on the board
        for (Map.Entry<Integer, Cell> entry : board.getCells().entrySet()) {
            Cell cell = entry.getValue();
            if(cell.getOccupiedBy() != null){
                Player player = cell.getOccupiedBy();
                System.out.println(player.getName() + " has occupied cell " + cell.getPosition() + " and lost the game!");
            }
        }

    }
}
