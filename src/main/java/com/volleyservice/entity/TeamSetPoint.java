package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Data
@Entity
public class TeamSetPoint {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private Team team;
    private Integer points;

    public TeamSetPoint(Team team) {
        this.team = team;
        this.points = 0;
    }

}
