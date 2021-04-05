package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Player {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    String name;
    String surname;
    int number;
    String teamName;
    LocalDate dateOfBirth;

    public Player(String name, String surname, int number, String teamName, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.teamName = teamName;
        this.dateOfBirth = dateOfBirth;
    }
}


