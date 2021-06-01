package com.volleyservice;


import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Component
public class DatabaseLoader {
    @Bean
    CommandLineRunner init(PlayerRepository repository) {

        return args -> {
            repository.save(new Player("Mat", "Anderson", 1, "USA", LocalDate.of(1991,1,1)));

        };

    }
}
