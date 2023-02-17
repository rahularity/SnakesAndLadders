package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.DieStrategies.MaxStrategy;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxStrategyTest {
    @Test
    public void testRoll() {
        // Test that the strategy returns a value in the range [1, 6] for a single die
        MaxStrategy strategy = new MaxStrategy();
        strategy.setTotalDies(1);
        int rollResult = strategy.roll();
        assertTrue(rollResult >= 1 && rollResult <= 6);

        // Test that the strategy returns a value in the range [1, 6] for two dice
        strategy.setTotalDies(2);
        int totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 1 && totalRollResult <= 6);

        // Test that the strategy returns a value in the range [1, 6] for three dice
        strategy.setTotalDies(3);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 1 && totalRollResult <= 6);

        // Test that the strategy returns a value in the range [1, 6] for four dice
        strategy.setTotalDies(4);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 1 && totalRollResult <= 6);

        // Test that the strategy returns a value in the range [1, 6] for five dice
        strategy.setTotalDies(5);
        totalRollResult = strategy.roll();
        assertTrue(totalRollResult >= 1 && totalRollResult <= 6);
    }
}
