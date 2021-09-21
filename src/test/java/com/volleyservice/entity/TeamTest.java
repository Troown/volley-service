package com.volleyservice.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TeamTest {

    @Test
    public void shouldCreateDefaultTeamNameBasedOnPlayersSurname() {
//        given
        Player player1 = new Player("Mateo", "Martino");
        Player player2 = new Player("Mateo", "Piano");
//        when
        Team newTeam = new Team(List.of(player1, player2));
//        then
        assertThat(newTeam.getTeamName()).isEqualTo("Martino/Piano");
    }

    @Test
    public void shouldEvaluateTeamRankingPoints() {
//        given
        Player player1 = new Player("Mateo", "Martino", 120);
        Player player2 = new Player("Mateo", "piano", 120);
//        when
        Team result = new Team(List.of(player1, player2));
//        then
        assertThat(result.getRankingPoints()).isEqualTo(240);
    }

}