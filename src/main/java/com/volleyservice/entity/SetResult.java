package com.volleyservice.entity;

import javax.persistence.*;

@Entity
public class SetResult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @OneToOne
    private TeamSetResult firstTeamSetResult;
    @OneToOne
    private TeamSetResult secondTeamSetResult;

}
