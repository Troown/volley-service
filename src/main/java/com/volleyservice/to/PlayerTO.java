package com.volleyservice.to;

import lombok.*;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Builder
@AllArgsConstructor
@Data
@Value
public class PlayerTO {
    Long id;
    String name;
    String surname;
    Integer rankingPoints;
    boolean isAdult;
}
