package com.volleyservice.mapper;

import com.volleyservice.entity.*;
import com.volleyservice.enums.Phase;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.TeamTO;
import com.volleyservice.to.TournamentTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TournamentMapperMSTest {
    @Mock
    private PlayerRepository playerRepository;
    private PlayerMapper playerMapper = new PlayerMapper();
    private TeamMapper teamMapper = new TeamMapper(playerRepository, playerMapper);
    @Test
    @Disabled
    void mapsToDTO() {
        Player p1 = new Player("Mateo", "Piano", 120);
        p1.setId(11L);
        p1.setDateOfBirth(LocalDate.of(1993, 1, 22));
        Team r1 = new Team(List.of(p1, p1));
        r1.setId(21L);
        List<Round> rounds = List.of(new Round(1,
                Phase.FIRST_ROUND,
                List.of(new Match(1, List.of(r1, r1)))));
        TournamentMapperMS mapper = Mappers.getMapper(TournamentMapperMS.class);
        Tournament tournament = new Tournament("My CUP", "MyCity");
        tournament.setRegisteredTeams(List.of(r1, r1));
        tournament.setRounds(rounds);
        TeamTO r1TO = teamMapper.mapsToTO(r1);
//        when
        TournamentTO tournamentTO = mapper.mapsToDTO(tournament);
//        then
        assertThat(tournamentTO.getTournamentName()).isEqualTo("My CUP");
        assertThat(tournamentTO.getCity()).isEqualTo("MyCity");
        assertThat(tournamentTO.getTeams()).containsExactlyInAnyOrder(r1TO, r1TO);
    }
}