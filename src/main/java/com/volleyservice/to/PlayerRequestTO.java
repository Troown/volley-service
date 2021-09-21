package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class PlayerRequestTO {
    @NotBlank(message = "PlayerName can not be empty")
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
}
