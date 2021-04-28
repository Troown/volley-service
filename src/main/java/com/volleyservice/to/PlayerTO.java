package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="player")
public class PlayerTO {
    String name;
    String surname;
    int number;
    String teamName;
    boolean isAdult;
}
