package com.example.demo;

import com.example.demo.Modeles.Role;
import com.example.demo.Modeles.User;
import com.example.demo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecuriteComApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuriteComApplication.class, args);

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {
			Role r1=userService.saveRole(new Role(1L,"USER"));
			Role r2=userService.saveRole(new Role(2L,"ADMIN"));


			if(userService.getUser("john")==null){
				User u1=userService.saveUser(new User(null, "John Travolta", "john","1234",new ArrayList<>()));
			}

			if(userService.getUser("Kim")==null){
				User u2=userService.saveUser(new User(null, "Kim Kardashian", "Kim","1234",new ArrayList<>()));
			}

			if(userService.getUser("will")==null){
				User u3=userService.saveUser(new User(null, "Will Smith", "will","1234",new ArrayList<>()));
			}

			userService.addRoleToUser("john",r1.getNom());
			userService.addRoleToUser("Kim", r2.getNom());
			userService.addRoleToUser("will", r1.getNom());
		};
	}

/**
 *
 * <dependency>
 * 			<groupId>org.hibernate</groupId>
 * 			<artifactId>hibernate-core</artifactId>
 * 			<version>4.1.4.Final</version>
 * 		</dependency>
 * 		<dependency>
 * 			<groupId>org.hibernate</groupId>
 * 			<artifactId>hibernate-entitymanager</artifactId>
 * 			<version>5.2.3.Final</version>
 * 		</dependency>
 */

}
