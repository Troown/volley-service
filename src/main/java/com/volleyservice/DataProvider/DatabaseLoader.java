package com.volleyservice.DataProvider;


import com.volleyservice.entity.Player;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.repository.*;
import com.volleyservice.service.TournamentService;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class DatabaseLoader {
    @Bean
    CommandLineRunner init(PlayerRepository playerRepo,
                           TeamRepository teamRepo,
                           TournamentRepository tournamentRepo,
                           RoundRepository roundRepo,
                           MatchRepository matchRepo,
                           TournamentService tournamentService) {
        return args -> {
            playerRepo.saveAll(List.of(
                    new Player("Jędrzej", "Brożyniak", 670),
                    new Player("Piotr", "Janiak", 670),
                    new Player("Jarosław", "Lech", 402),
                    new Player("Michał", "Korycki", 510),
                    new Player("Jakub", "Zdybek", 396),
                    new Player("Paweł", "Lewandowski", 490),
                    new Player("Bartosz", "Lewicki", 424),
                    new Player("Tomasz", "Maziarek", 424),
                    new Player("Martin", "Chiniewicz", 450),
                    new Player("Robert", "Kozłowski", 202),
                    new Player("Andrzej", "Paszkowski", 294),
                    new Player("Adrian", "Białecki", 338),
                    new Player("Maciej", "Kałuża", 490),
                    new Player("Kamil", "Radzikowski", 104),
                    new Player("Mikołaj", "Miszczuk", 324),
                    new Player("Miłosz", "Kruk", 240),
                    new Player("Linas", "Petravičius", 134),
                    new Player("Edvinas", "Vaskelis", 130),
                    new Player("Michał", "Głowacki", 68),
                    new Player("Michał", "Witkoś", 152),
                    new Player("Mateusz", "Podborączyński", 76),
                    new Player("Adam", "Lorenc", 76),
                    new Player("Tomasz", "Wołoszuk", 44),
                    new Player("Kacper", "Bobrowski", 44),
                    new Player("Tobiasz", "Kaniewski", 40),
                    new Player("Maciej", "Włodarczyk", 24),
                    new Player("Aleksander", "Czachorowski", 38),
                    new Player("Filip", "Lejawa", 20),
                    new Player("Damian", "Załęski", 16),
                    new Player("Mariusz", "Magnuszewski", 16),
                    new Player("Wojtek", "Iwanowicz", 0),
                    new Player("Mariusz", "Szlejter", 0),
                    new Player("Piotr", "Ilewicz", 308),
                    new Player("Piotr", "Groszek", 250),
                    new Player("Stanisław", "Wawrzyńczyk", 164),
                    new Player("Marcin", "Kapuśniak", 304),
                    new Player("Janusz", "Prus", 302),
                    new Player("Maciej", "Okrzeja", 94),
                    new Player("Jakub", "Nowak", 174),
                    new Player("Mateusz", "Florczyk", 196),
                    new Player("Tomasz", "Jaroszczak", 204),
                    new Player("Mikołaj", "Kaczmarek", 0),
                    new Player("Jakub", "Kurowski", 104),
                    new Player("Jacek", "Rejno", 36),
                    new Player("Kamil", "Waszczuk", 68),
                    new Player("Daniel", "Popiela", 32),
                    new Player("Dawid", "Strzyżewski", 34),
                    new Player("Piotr", "Staniszewski", 20),
                    new Player("Robert", "Juchnevic", 40),
                    new Player("Artur", "Vasiljev", 0),
                    new Player("Konrad", "Chudek", 38),
                    new Player("Mateusz", "Prudzynski", 0),
                    new Player("Jakub", "Krzemiński", 4),
                    new Player("Beniamin", "Olechnowicz", 20),
                    new Player("Łukasz", "Szypryt", 12),
                    new Player("Patryk", "Szulecki", 12),
                    new Player("Jakub", "Ćwiklewski", 10),
                    new Player("Łukasz", "Leszczyński", 10),
                    new Player("Szymon", "Beta", 0),
                    new Player("Olaf", "Łukasik", 4),
                    new Player("Gilbertas", "Kerpė", 0),
                    new Player("Toms", "Benjavs", 0),
                    new Player("Mateusz", "Kańczok", 1000)));

            for (int i = 1; i < 32; i = i + 2) {
                teamRepo.save(new Team(List.of(
                        playerRepo.findById((long) i).get(),
                        playerRepo.findById((long) (i + 1)).get()
                )));
            }

            tournamentRepo.save(new Tournament("Augustów Cup"));

            List<Team> teams = (List<Team>) teamRepo.findAll();

            Tournament updatingTournament = tournamentRepo.findById(1L).get();

            updatingTournament.setRegisteredTeams(teams);

            tournamentRepo.save(updatingTournament);


        };
    }

}
