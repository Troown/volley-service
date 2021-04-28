package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Player {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;
    private String surname;
    private int number;
    private String teamName;
    private LocalDate dateOfBirth;

    public Player(String name, String surname, int number, String teamName, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.teamName = teamName;
        this.dateOfBirth = dateOfBirth;
    }
}


