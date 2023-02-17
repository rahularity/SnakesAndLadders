package com.phonepe.SnakesAndLadders.DieStrategies;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface IDieStrategy {
    int roll();

    default List<Integer> rollEachDie(int totalDies) {
        List<Integer> rolls = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < totalDies; i++) {
            int roll = rand.nextInt(6) + 1; // roll a die with 6 sides
            rolls.add(roll);
        }

        return rolls;
    }

}
