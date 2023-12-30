package com.banking.bankapi;

import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootBankingAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBankingAppApiApplication.class, args);
    }

//    	@Bean     // CommandLineRunner is an interface used to indicate that a bean should run when it is contained within a SpringApplication.
//        CommandLineRunner run(UserRepository userRepository) {
//            return args -> {
//			userRepository.save(UserDto.toEntity(UserDto.builder().email("johncena@gmail.com").firstname("John").lastname("Cena").password("123456").build()));
//		};
//	}
}
