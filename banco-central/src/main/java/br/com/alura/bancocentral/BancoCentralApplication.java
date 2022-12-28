package br.com.alura.bancocentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class BancoCentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoCentralApplication.class, args);
	}

}
