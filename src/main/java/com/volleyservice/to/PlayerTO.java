package com.volleyservice.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerTO {
    Long id;
    String name;
    String surname;
    Integer rankingPoints;
    boolean isAdult;
}
