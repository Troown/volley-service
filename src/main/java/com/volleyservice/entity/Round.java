package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Round {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer roundNumber;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Match> matches;

    private Phase phase;

    public Round(Integer roundNumber, List<Match> matches) {
        this.roundNumber = roundNumber;
        this.matches = matches;
    }
}
