package com.volleyservice.entity;

import lombok.*;
import org.apache.catalina.LifecycleState;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

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

    public SetResult(TeamSetPoint firstTeamSetResult, TeamSetPoint secondTeamSetResult) {
        this.firstTeamSetResult = firstTeamSetResult;
        this.secondTeamSetResult = secondTeamSetResult;
    }

    public Optional<Team> getWinnerOfSet() {
        return getWinner(firstTeamSetResult, secondTeamSetResult).map(TeamSetPoint::getTeam);
    }

    public Optional<Team> getLoserOfSet() {
        return List.of(firstTeamSetResult.getTeam(),
                secondTeamSetResult.getTeam()).stream()
                .filter(team -> !Optional.of(team).equals(getWinnerOfSet()) && getWinnerOfSet().isPresent())
                .findFirst();
    }

    private Optional<TeamSetPoint> getWinner(TeamSetPoint firstTeamSetResult, TeamSetPoint secondTeamSetResult) {
        if (firstTeamSetResult.compareTo(secondTeamSetResult) == 1) {
            return Optional.of(firstTeamSetResult);
        } else if (firstTeamSetResult.compareTo(secondTeamSetResult) == -1) {
            return Optional.of(secondTeamSetResult);
        } else return Optional.empty();
    }
}

