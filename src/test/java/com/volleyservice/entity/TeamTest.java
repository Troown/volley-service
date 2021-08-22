package com.volleyservice.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TeamTest {

    @Test
    public void shouldCreateTeamNameBasedOnPlayersSurname() {
//        given
        Player player1 = new Player("Mateo", "Martino");
        Player player2 = new Player("Mateo", "Piano");
        Team team = new Team(List.of(player1, player2));

//        when
        String defaultName = team.getTeamName();

//        then
        assertThat(defaultName).isEqualTo("Martino/Piano");
    }

    @Test
    public void shouldEvaluateTeamRankingPoints() {
//        given
        Player player1 = new Player("Mateo", "Martino", 120);
        Player player2 = new Player("Mateo", "piano", 120);
        Team team = new Team(List.of(player1, player2));
//        when
        Integer teamPoints = team.evaluateRankingPoints();

//        then
        assertThat(teamPoints).isEqualTo(240);
    }

    @Test
    public void shouldEvaluateTeamRankingPointsWhenDoesNotDefine() {
//        given
        Player player1 = new Player("Mateo", "Martino", 120);
        Player player2 = new Player("Mateo", "piano");
        Team team = new Team(List.of(player1, player2));
//        when
        Integer teamPoints = team.evaluateRankingPoints();
//        then
        assertThat(teamPoints).isEqualTo(120);
    }
}