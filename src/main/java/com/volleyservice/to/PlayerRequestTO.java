package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerRequestTO {
    String name;
    String surname;
    int number;
    String teamName;
    LocalDate dateOfBirth;
}
