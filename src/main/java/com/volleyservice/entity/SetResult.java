package com.volleyservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Data
@Entity
public class SetResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private TeamSetPoint firstTeamSetResult;
    @OneToOne(cascade = {CascadeType.ALL})
    private TeamSetPoint secondTeamSetResult;
    private int lastPoint;

    public SetResult(TeamSetPoint firstTeamSetResult, TeamSetPoint secondTeamSetResult) {
        this.firstTeamSetResult = firstTeamSetResult;
        this.secondTeamSetResult = secondTeamSetResult;
    }

    public SetResult(TeamSetPoint firstTeamSetResult, TeamSetPoint secondTeamSetResult, int lastPoint) {
        this.firstTeamSetResult = firstTeamSetResult;
        this.secondTeamSetResult = secondTeamSetResult;
        this.lastPoint = lastPoint;
    }

    private boolean isTwoPointsDifferent() {
        return Math.abs(firstTeamSetResult.getPoints() - secondTeamSetResult.getPoints()) >= 2;
    }

    private boolean isLastPointAchieved() {
        return this.firstTeamSetResult.getPoints() >= this.lastPoint ||
                this.secondTeamSetResult.getPoints() >= this.lastPoint;
    }

    public Optional<Team> getSetWinner() {
        if (isTwoPointsDifferent() && isLastPointAchieved()) {
            return getTeamWithHigherNumberOfPoints();
        } else {
            return Optional.empty();
        }
    }

    public Optional<Team> getSetLoser() {
        return List.of(this.firstTeamSetResult.getTeam(),
                this.secondTeamSetResult.getTeam())
                .stream()
                .filter(team -> getSetWinner().isPresent() && !team.equals(getSetWinner().get()))
                .findFirst();
    }

    private Optional<Team> getTeamWithHigherNumberOfPoints() {
        if (firstTeamSetResult.compareTo(secondTeamSetResult) > 0) {
            return Optional.of(firstTeamSetResult.getTeam());
        } else if (firstTeamSetResult.compareTo(secondTeamSetResult) < 0){
            return Optional.of(secondTeamSetResult.getTeam());
        } else return Optional.empty();
    }
}

