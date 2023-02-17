package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SnakesAndLaddersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnakesAndLaddersApplication.class, args);

		try {
			GameService.main(args);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
