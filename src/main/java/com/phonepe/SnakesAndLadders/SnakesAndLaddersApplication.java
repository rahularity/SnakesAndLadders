package com.phonepe.SnakesAndLadders;

import com.phonepe.SnakesAndLadders.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnakesAndLaddersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnakesAndLaddersApplication.class, args);
		GameService.main(args);
	}

}
