package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity
public class Serve {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private PracticeSession practiceSession; //(date, index of practice session)
    private String teamName;
    private String number;
    private int speed; //[km/h]

    @Pattern(regexp="[#+/-=]",message="invalid code")
    private char result; //"#"=as; "/"=passing through the net; "-"=received perfect or positive; "="=error;

    public Serve(String teamName, String number, int speed, char result) {
        practiceSession = new PracticeSession();

        this.teamName = teamName;
        this.number = number;
        this.speed = speed;
        this.result = result;
    }
}
