package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.DoubleElimination;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Team;
import com.volleyservice.enums.Seed;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AssignTeamsInFirstRoundTest {
    @Mock
    Team r1;
    @Mock
    Team r2;
    @Mock
    Team r3;
    @Mock
    Team r4;
    @Mock
    Team r5;
    @Mock
    Team r6;
    @Mock
    Team r7;
    @Mock
    Team r8;
    @Mock
    Team r9;
    @Mock
    Team r10;
    @Mock
    Team r11;
    @Mock
    Team r12;
    @Mock
    Team r13;
    @Mock
    Team r14;
    @Mock
    Team r15;
    @Mock
    Team r16;

    @Mock
    TournamentRepository tournamentRepository;

    DoubleElimination doubleElimination = new DoubleElimination();

    RoundService roundService = new RoundService(tournamentRepository, doubleElimination);
    Map<Seed, Team> getSeedsToTeamMap() {
        Map<Seed, Team> seeds = new java.util.HashMap<>(Map.of(
                Seed.R1, r1,
                Seed.R2, r2,
                Seed.R3, r3,
                Seed.R4, r4,
                Seed.R5, r5,
                Seed.R6, r6,
                Seed.R7, r7,
                Seed.R8, r8,
                Seed.R9, r9,
                Seed.R10, r10));
        seeds.put(Seed.R11, r11);
        seeds.put(Seed.R12, r12);
        seeds.put(Seed.R13, r13);
        seeds.put(Seed.R14, r14);
        seeds.put(Seed.R15, r15);
        seeds.put(Seed.R16, r16);
        return seeds;
    }

    @Test
    @DisplayName("Put teams in first round based on seeds")
    void putTeams() {
//        given
        var seedsToTeamMap = getSeedsToTeamMap();
        Round firstRound = new Round();
        firstRound.setMatches(List.of(
                new Match(1),
                new Match(2),
                new Match(3),
                new Match(4),
                new Match(5),
                new Match(6),
                new Match(7),
                new Match(8)));
//        when
         roundService.putTeamsInFirstRound(firstRound, seedsToTeamMap);

//        then
        assertThat(firstRound.getMatches().get(0).getTeams()).containsExactlyInAnyOrder(r1, r16);
    }

}
