package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
@Data
@Entity
public class MatchSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    private int setNumber;
    @OneToOne(cascade = {CascadeType.ALL})
    private SetResult setResult;

    public MatchSet(int setNumber, List<Team> teams) {
        this.setNumber = setNumber;
        this.setResult = new SetResult(teams);
    }
}
