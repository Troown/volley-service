package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class MatchSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    private int setNumber;
    @OneToOne
    private SetResult setResult;
//    private Map<Player, List<Cards>> playerToCards;


    public Optional<Team> getWinnerOfSet() {
        return Optional.empty();
    }

    public Optional<Team> getLoserOfSet() {
        return Optional.empty();
    }

//    public boolean isTwoPointsDeference() {
//        return this.teamToPointsMap.values().stream().
//                reduce((subtotal, element) -> subtotal - element).map(Math::abs).orElse(0) >= 2;
//    }
}
