package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.DieStrategies.MaxStrategy;
import com.phonepe.SnakesAndLadders.DieStrategies.MinStrategy;
import com.phonepe.SnakesAndLadders.DieStrategies.SumStrategy;
import org.junit.jupiter.api.Test;

public class GameServiceTest {

    @Test
    public void diceRollMaxStrategy(){
        MaxStrategy maxStrategy = new MaxStrategy();
        maxStrategy.setTotalDies(4);

        int max = maxStrategy.roll();
        System.out.println("Max is : " + max);
        assert(max >= 1 && max <= 6);
    }

    @Test
    public void diceRollMinStrategy(){
        MinStrategy minStrategy = new MinStrategy();
        minStrategy.setTotalDies(4);

        int min = minStrategy.roll();
        System.out.println("Min is : " + min);
        assert(min >= 1 && min <= 6);
    }

    @Test
    public void diceRollSumStrategy(){
        SumStrategy sumStrategy = new SumStrategy();
        sumStrategy.setTotalDies(4);

        int sum = sumStrategy.roll();
        System.out.println("Sum is : " + sum);
        assert(sum >= 4 && sum <= 24);
    }

}
