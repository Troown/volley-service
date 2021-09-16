package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.DoubleElimination;
import com.volleyservice.SystemDataProvider.MatchesRelation;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.enums.Phase;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
public class SetTeamsInMatchMethodTest {
    @Mock
    private TournamentRepository tournamentRepository;
    @InjectMocks
    private MatchService matchService;
    @Mock
    private Team team1;
    @Mock
    private Team team2;

    private final DoubleElimination doubleElimination = new DoubleElimination();

    @Disabled
    @Test
    public void setTeamsInCorrespondingMatches() {
//        given
        Tournament tournament = new Tournament("My CUP");
        Match sourceMatch = new Match(1, List.of(team1, team2));
        Match match9 = new Match(9);
        Match match16 = new Match(16);
        tournament.setRounds(List.of(new Round(1, Phase.FIRST_ROUND, List.of(sourceMatch, match9, match16))));
        List<MatchesRelation> relations = doubleElimination.getMatchesRelations();

//        when
        List<Match> updatedMatches = matchService.updateRelatedMatches(sourceMatch, tournament, relations);
//        then
        assertThat(updatedMatches.size()).isEqualTo(2);
        assertThat(updatedMatches.stream().filter(match -> match.getMatchNumber() == 9)
        .findFirst().get().getTeams()).contains(sourceMatch.getWinner().get());
    }

}
