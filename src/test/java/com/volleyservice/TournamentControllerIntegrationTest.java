package com.volleyservice;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.TeamRequestTO;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VolleyServiceApplication.class})
@WebAppConfiguration(value = "")
public class TournamentControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentService tournamentService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    public void addTournamentToDB() {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/tournaments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tournamentName\": \"Augustow CUP\"}"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/hal+json"))
                .andReturn();
        var content = result.getResponse().getContentAsString();

        JSONObject jsonResponse = new JSONObject(content);

        assertThat(jsonResponse.get("tournamentName")).isEqualTo("Augustow CUP");

    }


    @Test
    public void addTeamToTournament() {
        generatePlayers();
        generateTeams();
        Tournament tournament = generateTournament();


        assertThat(playerRepository.findById(3L).get().getSurname()).isEqualTo("Lech");
        assertThat(teamRepository.findById(1L).get().getTeamName()).isEqualTo("Brożyniak/Janiak");
    }

    private Tournament generateTournament() {
        Tournament tournament = tournamentRepository.save(new Tournament("Plaza Open"));
        List<Team> teams = (List<Team>) teamRepository.findAll();

        tournament.setRegisteredTeams(teams);

        return tournamentRepository.save(tournament);
    }

    private void generatePlayers() {
        playerRepository.saveAll(List.of(
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
    }

    private void generateTeams() {
        for (int i = 1; i < 32; i = i + 2) {
            teamRepository.save(new Team(List.of(
                    playerRepository.findById((long) i).get(),
                    playerRepository.findById((long) (i + 1)).get()
            )));
        }

    }
}







