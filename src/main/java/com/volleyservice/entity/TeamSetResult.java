package com.volleyservice.entity;

import javax.persistence.*;

@Entity
public class TeamSetResult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @OneToOne
    private Team team;
    private Integer result;

}
