package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    String name;
    String surname;
    Integer rankingPoints;
    boolean isAdult;
}
