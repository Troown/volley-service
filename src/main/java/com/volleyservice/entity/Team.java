package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TEAM")
public class Team {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToMany
    private List<Player> players;
    private String teamName;
    private int rankingPoints;
    
    public Team(List<Player> players ) {
        this.players = players;
        this.teamName = createDefaultName(players);
        this.rankingPoints = evaluateRankingPoints(players);
    }

    public Team(List<Player> players, String teamName) {
        this.players = players;
        this.teamName = teamName;
        this.rankingPoints = evaluateRankingPoints(players);
    }

    private String createDefaultName(List<Player> players) {
        List<String> surnames = players.stream().map(Player::getSurname).collect(Collectors.toList());
        return surnames.get(0)+ "/" + surnames.get(1);
    }

    private Integer evaluateRankingPoints(List<Player> players) {
        return players.stream().mapToInt(Player::getRankingPoints).sum();
    }

}
