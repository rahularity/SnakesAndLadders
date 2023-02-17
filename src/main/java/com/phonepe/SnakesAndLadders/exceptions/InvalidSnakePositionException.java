package com.phonepe.SnakesAndLadders.exceptions;

public class InvalidSnakePositionException extends RuntimeException {
    public InvalidSnakePositionException(String message) {
        super(message);
    }
}