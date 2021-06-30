package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


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

    public SetResult(List<Team> teams) {
        this.firstTeamSetResult = new TeamSetPoint(teams.get(0));
        this.secondTeamSetResult = new TeamSetPoint(teams.get(1));
    }

    public Optional<Team> getWinnerOfSet() {
        if (firstTeamSetResult.getPoints() >
                secondTeamSetResult.getPoints()) {
            return Optional.of(firstTeamSetResult.getTeam());
        }
        else return Optional.of(secondTeamSetResult.getTeam());
    }
    public Optional<Team> getLoserOfSet() {
        if (firstTeamSetResult.getPoints() <
                secondTeamSetResult.getPoints()) {
            return Optional.of(firstTeamSetResult.getTeam());
        }
        else return Optional.of(secondTeamSetResult.getTeam());
    }
}
