package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class PlayerRequestTO {
    String name;
    String surname;
    LocalDate dateOfBirth;

}
