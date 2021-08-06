package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        if (firstTeamSetResult.getPoints() > secondTeamSetResult.getPoints()) {
            return Optional.of(firstTeamSetResult.getTeam());
        } else if (secondTeamSetResult.getPoints() > firstTeamSetResult.getPoints()) {
            return Optional.of(secondTeamSetResult.getTeam());
        } else return Optional.empty();
    }
    public Optional<Team> getLoserOfSet() {
        if (firstTeamSetResult.getPoints() > secondTeamSetResult.getPoints()) {
            return Optional.of(secondTeamSetResult.getTeam());
        } else if (secondTeamSetResult.getPoints() > firstTeamSetResult.getPoints()) {
            return Optional.of(firstTeamSetResult.getTeam());
        } else return Optional.empty();
    }
}

