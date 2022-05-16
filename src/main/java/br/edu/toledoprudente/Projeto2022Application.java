package br.edu.toledoprudente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Projeto2022Application {

	public static void main(String[] args) {
		System.out.print("Senha: " + new BCryptPasswordEncoder().encode("123456"));

		SpringApplication.run(Projeto2022Application.class, args);
	}

}
