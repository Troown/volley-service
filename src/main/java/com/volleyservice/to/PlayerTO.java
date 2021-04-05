package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    String name;
    String surname;
    int number;
    String teamName;
    boolean isAdult;
}
