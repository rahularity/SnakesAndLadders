package com.phonepe.SnakesAndLadders.DieStrategies;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class MaxStrategy implements IDieStrategy{

    private int totalDies;

    @Override
    public int roll() {
        List<Integer> rollResult = rollEachDie(totalDies);
        return rollResult.stream().mapToInt(Integer::intValue).max().orElse(0);
    }
}
