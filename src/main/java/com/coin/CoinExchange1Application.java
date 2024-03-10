package com.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo","com.coin.user"})
public class CoinExchange1Application {

	public static void main(String[] args) {
		SpringApplication.run(CoinExchange1Application.class, args);
	}

}
