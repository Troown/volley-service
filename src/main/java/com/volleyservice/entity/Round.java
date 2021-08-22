package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Round {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer roundNumber;

    private Phase phase;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Match> matches;

    public Round(Integer roundNumber, Phase phase, List<Match> matches) {
        this.roundNumber = roundNumber;
        this.phase = phase;
        this.matches = matches;
    }
}
