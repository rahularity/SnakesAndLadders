package com.phonepe.SnakesAndLadders.exceptions;

public class InvalidLadderPositionException extends RuntimeException {
    public InvalidLadderPositionException(String message) {
        super(message);
    }
}