package br.com.bgrbarbosa.livro_caixa_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LivroCaixaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivroCaixaApiApplication.class, args);
	}

}
