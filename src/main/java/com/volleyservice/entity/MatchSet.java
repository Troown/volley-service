package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@NoArgsConstructor
@Data
@Entity(name = "SET")
public class MatchSet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    private int setNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    private SetResult setResult;


    public MatchSet(int setNumber, SetResult setResult) {
        this.setNumber = setNumber;
        this.setResult = setResult;
    }

}
