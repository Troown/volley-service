package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class TeamSetPoint implements Comparable<TeamSetPoint> {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Team team;

    private Integer points;


    public TeamSetPoint(Team team, Integer points) {
        this.team = team;
        this.points = points;
    }


    @Override
    public int compareTo(@NotNull TeamSetPoint o) {
        if (this.getPoints() > o.getPoints()) {
            return 1;
        } else if (this.getPoints().equals(o.getPoints())) {
            return 0;
        }else return -1;
    }
}
