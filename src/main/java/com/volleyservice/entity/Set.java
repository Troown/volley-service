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
import java.util.stream.Stream;

import static java.lang.Math.abs;

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
        this.setResult = setResult;
    }
    public Optional<Team> getSetWinner() {
        if (isTwoPointsDifferent() && isLastPointAchieved()) {
            return getTeamWithHigherNumberOfPoints();
        } else {
            return Optional.empty();
        }
    }
    public Optional<Team> getSetLoser() {
        return Stream.of(setResult.getFirstTeamSetResult().getTeam(),
                        setResult.getSecondTeamSetResult().getTeam())
                .filter(team -> getSetWinner().isPresent() && !team.equals(getSetWinner().get()))
                .findFirst();
    }
    private boolean isTwoPointsDifferent() {
        return abs(this.setResult.getFirstTeamSetResult().getPoints() -
                this.setResult.getSecondTeamSetResult().getPoints()) >= 2;
    }
    private boolean isLastPointAchieved() {
        return this.setResult.getFirstTeamSetResult().getPoints() >= this.lastPoint ||
                this.setResult.getSecondTeamSetResult().getPoints() >= this.lastPoint;
    }
    private Optional<Team> getTeamWithHigherNumberOfPoints() {
        if (setResult.getFirstTeamSetResult().compareTo(setResult.getSecondTeamSetResult()) > 0) {
            return Optional.of(setResult.getFirstTeamSetResult().getTeam());
        } else if (setResult.getFirstTeamSetResult().compareTo(setResult.getSecondTeamSetResult()) < 0){
            return Optional.of(setResult.getSecondTeamSetResult().getTeam());
        } else return Optional.empty();
    }
}
