package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.DieStrategies.SumStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SumStrategyTest {
    @Test
    public void testRoll() {
        // Test that the strategy returns a value in the range [1, 6] for a single die
        SumStrategy strategy = new SumStrategy();
        strategy.setTotalDies(1);
        int rollResult = strategy.roll();
        assertTrue(rollResult >= 1 && rollResult <= 6);

        // Test that the strategy returns a value in the range [2, 12] for two dice
        strategy.setTotalDies(2);
        int totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 2 && totalRollResult <= 12);

        // Test that the strategy returns a value in the range [3, 18] for three dice
        strategy.setTotalDies(3);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 3 && totalRollResult <= 18);

        // Test that the strategy returns a value in the range [4, 24] for four dice
        strategy.setTotalDies(4);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 4 && totalRollResult <= 24);

        // Test that the strategy returns a value in the range [5, 30] for five dice
        strategy.setTotalDies(5);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 5 && totalRollResult <= 30);
    }
}
