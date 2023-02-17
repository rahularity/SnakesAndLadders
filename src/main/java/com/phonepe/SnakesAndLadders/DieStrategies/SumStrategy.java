package com.phonepe.SnakesAndLadders.DieStrategies;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
@Data
public class SumStrategy implements IDieStrategy{

    @Value("${config.numberOfDies}")
    private int totalDies;

    @Override
    public int roll() {
        List<Integer> rollResult = rollEachDie(totalDies);
        // TODO: logging
        return rollResult.stream().mapToInt(Integer::intValue).sum();
    }
}
