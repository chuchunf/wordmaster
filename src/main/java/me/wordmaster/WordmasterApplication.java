package me.wordmaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.wordmaster.dao")
public class WordmasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordmasterApplication.class, args);
	}
}
