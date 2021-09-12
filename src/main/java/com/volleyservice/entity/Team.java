package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TEAM")
public class Team {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToMany
    private List<Player> players;

    private String teamName;

    private int rankingPoints;
    
    public Team(List<Player> players ) {
        this.players = players;
        this.teamName = getDefaultName(players);
        this.rankingPoints = evaluateRankingPoints();
    }

    public Team(List<Player> players, String teamName) {
        this.players = players;
        this.teamName = teamName;
        this.rankingPoints = evaluateRankingPoints();
    }

    private String getDefaultName(List<Player> players) {
        return players.stream().map(Player::getSurname)
                .collect(Collectors.joining("/"));
    }

    private Integer evaluateRankingPoints() {
        return this.players.stream().mapToInt(Player::getRankingPoints).sum();
    }

}
