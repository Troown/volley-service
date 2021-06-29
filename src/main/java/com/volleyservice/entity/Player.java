package com.volleyservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PLAYER")
public class Player {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Integer rankingPoints;


    public Player(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.rankingPoints = 0;
        this.dateOfBirth = LocalDate.parse("1900-01-01");
    }

    public Player(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.rankingPoints = 0;
    }
}


