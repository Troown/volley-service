package com.volleyservice.mapper;

import com.volleyservice.entity.*;
import com.volleyservice.to.MatchTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MatchMapperTest {
    MatchMapper matchMapper = new MatchMapper();

    Team team1 = new Team(List.of(
            new Player("Mateo", "Martino"),
            new Player("Mateo", "Piano")));
    Team team2 = new Team(List.of(
            new Player("Jake", "Gibb"),
            new Player("Jake", "Zdyb")));

    @Test
    void shouldMapsMatchToTransferObjectWhenResultIsNotSet() {
//        given
        Match match = new Match(1, List.of(team1, team2));
//        when
        MatchTO result = matchMapper.mapsToTO(match);
//        then
        assertThat(result.getTeamToSets())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        team1.getTeamName(), 0L,
                        team2.getTeamName(), 0L));
    }
    @Test
    void shouldMapsMatchToTransferObjectWhenMatchContainOneFinishedMatchSet() {
//        given
        Match match = new Match(1, List.of(team1, team2));
        match.setSets(List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 21),
                        new TeamSetPoint(team2, 19)))));
//        when
        MatchTO result = matchMapper.mapsToTO(match);
//        then
        assertThat(result.getTeamToSets())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        team1.getTeamName(), 1L,
                        team2.getTeamName(), 0L));
    }
    @Test
    void shouldMapsMatchToTransferObjectWhenMatchContainOneNotFinishedMatchSet() {
//        given
        Match match = new Match(1, List.of(team1, team2));
        match.setSets(List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 21),
                        new TeamSetPoint(team2, 21)))));
//        when
        MatchTO result = matchMapper.mapsToTO(match);
//        then
        assertThat(result.getTeamToSets())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        team1.getTeamName(), 0L,
                        team2.getTeamName(), 0L));
    }

    @Test
    void shouldMapsMatchToTransferObjectWhenResultIs2to1() {
//        given
        Match match = new Match(1, List.of(team1, team2));
        match.setSets(List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 21),
                        new TeamSetPoint(team2, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 19),
                        new TeamSetPoint(team2, 21))),
                new Set(3, 15,
                        new SetResult(
                        new TeamSetPoint(team1, 15),
                        new TeamSetPoint(team2, 11)))));
//        when
        MatchTO result = matchMapper.mapsToTO(match);
//        then
        assertThat(result.getTeamToSets())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        team1.getTeamName(), 2L,
                        team2.getTeamName(), 1L));
    }

    @Test
    void shouldMapsMatchToTransferObjectWhenMatchContainsNotFinishedSet() {
//        given
        Match match = new Match(1, List.of(team1, team2));
        match.setSets(List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 21),
                        new TeamSetPoint(team2, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(team1, 19),
                        new TeamSetPoint(team2, 21))),
                new Set(3, 15,
                        new SetResult(
                        new TeamSetPoint(team1, 15),
                        new TeamSetPoint(team2, 15)))));
//        when
        MatchTO result = matchMapper.mapsToTO(match);
//        then
        assertThat(result.getTeamToSets())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        team1.getTeamName(), 1L,
                        team2.getTeamName(), 1L));
    }
}