package com.volleyservice.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PracticeSession { //maybe this class should be under Serve class
    private LocalDate date;
    private int exerciseNumber;

    PracticeSession() {
        this.date = java.time.LocalDate.now();
        this.exerciseNumber = 1;
    }

    PracticeSession(int exerciseNumber) {
        this.date = java.time.LocalDate.now();
        this.exerciseNumber = exerciseNumber;
    }

}
