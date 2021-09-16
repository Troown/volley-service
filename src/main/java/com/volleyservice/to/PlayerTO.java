package com.volleyservice.to;

import lombok.*;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Builder
@AllArgsConstructor
@Data
@Value
public class PlayerTO {
    private Long id;
    private String name;
    private String surname;
    private Integer rankingPoints;
    private boolean isAdult;
}
