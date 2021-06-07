package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BVBTeam {

    private List<Player> players;
    private String teamName;
    private int rankingPoints;


    public BVBTeam(String teamName) {
        this.teamName = teamName;
        this.players = List.of(new Player("Player1"), new Player("Player2"));
        this.rankingPoints = 0;
    }
}
