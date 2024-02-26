package com.fsepulvedadev;


import java.util.HashSet;

import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fsepulvedadev.repository.IRoleRepository;
import com.fsepulvedadev.repository.IUserRepository;
import com.fsepulvedadev.models.ApplicationUser;
import com.fsepulvedadev.models.Role;

@SpringBootApplication
public class AuthBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBackendApplication.class, args);


	}

	@Bean
	CommandLineRunner run (IRoleRepository RoleRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {

			if(RoleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = RoleRepository.save(new Role("ADMIN"));
			RoleRepository.save(new Role("USER"));


			Set<Role> roles = new HashSet<Role>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);

			userRepository.save(admin);
			
		};
	}


}

