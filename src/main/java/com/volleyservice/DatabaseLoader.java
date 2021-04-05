package com.volleyservice;


import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader {
    @Bean
    CommandLineRunner init(PlayerRepository repository) {

        return args -> {
            repository.save(new Player("Mat", "Anderson", 4, "MOS", LocalDate.of(2000,1,1)));
            repository.save(new Player("Ivan", "Mil", 14, "MOS", LocalDate.of(2000,1,1)));
        };
    }
}
