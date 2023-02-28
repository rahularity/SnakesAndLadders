package com.phonepe.SnakesAndLadders.service;

import com.phonepe.SnakesAndLadders.DieStrategies.MaxStrategy;
import com.phonepe.SnakesAndLadders.DieStrategies.MinStrategy;
import com.phonepe.SnakesAndLadders.DieStrategies.SumStrategy;
import com.phonepe.SnakesAndLadders.Game;
import com.phonepe.SnakesAndLadders.PlayerTurnStrategy.RoundRobinStrategy;
import com.phonepe.SnakesAndLadders.enums.EntityName;
import com.phonepe.SnakesAndLadders.enums.PlayerStatus;
import com.phonepe.SnakesAndLadders.model.Board;
import com.phonepe.SnakesAndLadders.model.Cell;
import com.phonepe.SnakesAndLadders.model.Player;
import com.phonepe.SnakesAndLadders.model.boardentity.IBoardEntity;
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

        Resource resource = new ClassPathResource("SnakeAndLadderInput");
        File file = resource.getFile();
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            // input snakes
            int totalSnakes = Integer.parseInt(br.readLine());
            while(totalSnakes-- != 0){
                String line = br.readLine();
                List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                board.setEntity(EntityName.SNAKE, positions.get(0), positions.get(1));
                boardEntityList.add(board.getEntity(positions.get(0)));
            }

            int totalLadders = Integer.parseInt(br.readLine());
            while(totalLadders-- != 0){
                String line = br.readLine();
                List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                board.setEntity(EntityName.LADDER, positions.get(0), positions.get(1));
                boardEntityList.add(board.getEntity(positions.get(0)));
            }

            int totalCrocodile = Integer.parseInt(br.readLine());
            while(totalCrocodile-- != 0){
                Integer pos = Integer.parseInt(br.readLine());
                // List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                board.setEntity(EntityName.CROCODILE, pos);
                boardEntityList.add(board.getEntity(pos));
            }

            int totalMines = Integer.parseInt(br.readLine());
            while(totalMines-- != 0){
                Integer pos = Integer.parseInt(br.readLine());
                // List<Integer> positions = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();

                board.setEntity(EntityName.MINE, pos);
                boardEntityList.add(board.getEntity(pos));
            }

            int totalPlayers = Integer.parseInt(br.readLine());
            while(totalPlayers-- != 0){
                String line = br.readLine();
                List<String> details = Arrays.stream(line.split(" ")).map(x -> x.toString()).toList();

                String playerName = details.get(0);
                Integer playerPosition = Integer.parseInt(details.get(1));
                board.addPlayer(playerPosition, playerName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(IBoardEntity boardEntity : boardEntityList){
            System.out.println(boardEntity.toString());
        }
        for(Player player : board.getPlayers()){
            System.out.println(player);
        }

        // GAME SETUP
        MaxStrategy maxDieStrategy = new MaxStrategy();
        MinStrategy minStrategy = new MinStrategy();
        SumStrategy sumStrategy = new SumStrategy();
        maxDieStrategy.setTotalDies(1);
        sumStrategy.setTotalDies(1);
        RoundRobinStrategy roundRobinPlayerTurnStrategy = new RoundRobinStrategy(board.getPlayers());
        Game game = new Game(sumStrategy, roundRobinPlayerTurnStrategy, board);

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
