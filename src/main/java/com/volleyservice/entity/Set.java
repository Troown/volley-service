package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@NoArgsConstructor
@Data
@Entity(name = "SET")
public class Set {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    private int setNumber;
    private int lastPoint;
    @OneToOne(cascade = {CascadeType.ALL})
    private SetResult setResult;

    public Set(int setNumber, int lastPoint, SetResult setResult) {
        this.setNumber = setNumber;
        this.lastPoint = lastPoint;
        this.setResult = new SetResult(
                setResult.getFirstTeamSetResult(),
                setResult.getSecondTeamSetResult(),
                lastPoint);
    }
}
