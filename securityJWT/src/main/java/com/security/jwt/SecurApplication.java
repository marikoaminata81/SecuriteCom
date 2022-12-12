package com.security.jwt;

import com.security.jwt.modele.role;
import com.security.jwt.modele.utilisateur;
import com.security.jwt.service.serviceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication

public class SecurApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurApplication.class, args);
	}
    @Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start( serviceImpl e ) throws Exception {
		return  args -> {

			e.ajouter(("admin"));
			e.ajouter("user");

			e.ajout(new utilisateur(null,"Admin","admin",new ArrayList<role>()));
			e.ajout(new utilisateur(null,"User","user",new ArrayList<role>()));
			e.addroleToUser("Admin","admin");
			e.addroleToUser("User","user");
		};
	}

}
